package com.example.shopping_user_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shopping_user_service.mapper.ShoppingUserMapper;
import com.itbaizhan.shopping_common.exception.BusException;
import com.itbaizhan.shopping_common.pojo.ShoppingUser;
import com.itbaizhan.shopping_common.result.CodeEnum;
import com.itbaizhan.shopping_common.service.ShoppingUserService;
import com.itbaizhan.shopping_common.util.Md5Util;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.concurrent.TimeUnit;

@DubboService
public class ShoppingUserServiceImpl implements ShoppingUserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ShoppingUserMapper shoppingUserMapper;

    @Override
    public void saveRegisterCheckCode(String phone, String checkCode) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("registerCode:"+phone,checkCode,300, TimeUnit.SECONDS);
    }

    @Override
    public void registerCheckCode(String phone, String checkCode) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object checkCodeRedis = valueOperations.get("registerCode:" + phone);
        if(!checkCode.equals(checkCodeRedis)){
            throw new BusException(CodeEnum.REGISTER_CODE_ERROR);
        }
    }

    @Override
    public void register(ShoppingUser shoppingUser) {
        String shoppingUserName = shoppingUser.getName();
        QueryWrapper<ShoppingUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",shoppingUserName);
        List<ShoppingUser> shoppingUserList = shoppingUserMapper.selectList(queryWrapper);
        if(shoppingUserList.size()>0 && shoppingUserList != null){
            throw new BusException(CodeEnum.REGISTER_REPEAT_NAME_ERROR);
        }

        String userPhone = shoppingUser.getPhone();
        QueryWrapper<ShoppingUser> queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("phone",userPhone);
        List<ShoppingUser> shoppingUserList1 = shoppingUserMapper.selectList(queryWrapper1);
        if(shoppingUserList1 != null && shoppingUserList1.size()> 0){
           throw  new BusException(CodeEnum.REGISTER_REPEAT_PHONE_ERROR);
        }
        shoppingUser.setStatus("Y");
        shoppingUser.setPassword(Md5Util.encode(shoppingUser.getPassword()));
        shoppingUserMapper.insert(shoppingUser);

    }

    @Override
    public String loginPassword(String username, String password) {

        QueryWrapper<ShoppingUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        ShoppingUser shoppingUser = shoppingUserMapper.selectOne(queryWrapper);
        if(shoppingUser == null) throw new BusException(CodeEnum.LOGIN_NAME_PASSWORD_ERROR);

        boolean verify = Md5Util.verify(password, shoppingUser.getPassword());
        if(!verify) throw new BusException(CodeEnum.LOGIN_NAME_PASSWORD_ERROR);
        return username;
    }

    @Override
    public void saveLoginCheckCode(String phone, String checkCode) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("loginCode:"+phone,checkCode,300,TimeUnit.SECONDS);
    }

    @Override
    public String loginCheckCode(String phone, String checkCode) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object object = valueOperations.get("loginCode:" + phone);
        if(object != null && !object.equals(checkCode)) throw new BusException(CodeEnum.LOGIN_NAME_PASSWORD_ERROR);
        QueryWrapper<ShoppingUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        ShoppingUser shoppingUser = shoppingUserMapper.selectOne(queryWrapper);
        return shoppingUser.getName();

    }

    @Override
    public String getName(String token) {
        return null;
    }

    /**
     * 查询手机状态是否正常
     * @param phone 手机号码
     */
    @Override
    public void checkPhone(String phone) {
        QueryWrapper<ShoppingUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        ShoppingUser selectOne = shoppingUserMapper.selectOne(queryWrapper);
        if(selectOne == null || "Y".equals(selectOne.getStatus() )) throw new BusException(CodeEnum.LOGIN_NOPHONE_ERROR);
    }

    @Override
    public ShoppingUser getLoginUser(Long id) {
        return null;
    }
}
