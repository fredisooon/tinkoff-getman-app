package com.example.getmanapp.utils;

import com.ongres.scram.common.bouncycastle.base64.Base64;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    private String type;

    private String data;

    public String getDecodedData() {
        return new String(Base64.decode(data));
    }
}
