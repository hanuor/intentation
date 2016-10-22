package com.hanuor.intentationandroid;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Intentation {
    private Context context;
    public Intentation(Context context){
        this.context = context;
    }

        public String toJsonString(Intent intent) throws JsonProcessingException {
            String getClassName = intent.getComponent().getClassName();
            String getContextName = context.getClass().getSimpleName() + ".this";
            HashMap<String, String> hashMap = new HashMap<String, String>();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(intent);
        }
    public String processIntent(Intent intentObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String getClassName = null;
        getClassName = intentObject.getComponent().getClassName();
        String getContextName = null;
        getContextName = context.getClass().getSimpleName() + ".this";
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("className",getClassName);
        hashMap.put("context",getContextName);
        Bundle bundle = intentObject.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();

            while (it.hasNext()) {
                String key = it.next();
                hashMap.put(key, bundle.get(key).toString());
            }
        }
        String intentString  = mapper.writeValueAsString(intentObject);
        StringBuilder a1S = new StringBuilder(mapper.writeValueAsString(hashMap));
        a1S.deleteCharAt(mapper.writeValueAsString(hashMap).length()-1);
        a1S.append(",");
        String s1t = a1S.toString();

        StringBuilder sb = new StringBuilder(intentString);
        sb.deleteCharAt(0);
        String retrString = sb.toString();
        StringBuilder newS = new StringBuilder();
        newS.append(s1t);
        newS.append(retrString);
        return newS.toString();
    }

}
