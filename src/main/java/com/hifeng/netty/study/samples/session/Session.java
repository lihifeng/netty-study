package com.hifeng.netty.study.samples.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lzh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
