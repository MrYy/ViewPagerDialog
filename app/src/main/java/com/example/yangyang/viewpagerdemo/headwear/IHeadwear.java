package com.example.yangyang.viewpagerdemo.headwear;

import java.util.List;

/**
 * Created by yangyang on 2017/2/17.
 */

public interface IHeadwear {
    //获取头饰的接口
    List<Headwear> getHeadwear();

    //不选择头饰的接口
    void selectNoneHeadwear();

    //选择某个头饰
    void selectHeadwear(Headwear headwear);

}
