package task6.creator;

import task6.input.InputSource;

public interface CreatorSource<T> {

    /**
     * create beer object and filling it
     *
     * @param inputSource
     * @return filling beer
     */
    T create(InputSource inputSource);
}
