package dao.impl;

import dao.ICaptchaDao;
import entity.Captcha;

import java.util.LinkedHashMap;
import java.util.Map;

public class CaptchaDaoImpl implements ICaptchaDao {
    private Map<Integer, Captcha> captcha;

    public CaptchaDaoImpl() {
        this.captcha = new LinkedHashMap<>();
    }

    @Override
    public Captcha getCaptcha(int key) {
        return captcha.get(key);
    }

    @Override
    public void removeCaptcha(int key) {
        captcha.remove(key);
    }

    @Override
    public Map<Integer, Captcha> getAllCaptches() {
        return captcha;
    }
}
