package com.wzz.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by wzz
 * @implNote 2020/10/20 11:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {

    private Integer roleId;

    private String username;

    private String password;

}
