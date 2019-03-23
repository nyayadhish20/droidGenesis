package com.nyayadhish.droidgenesis.lib;


import java.util.HashMap;

public class CustomHashMap {
    private HashMap<String, String> map = new HashMap<>();


    public class Builder {
        public Builder() {
            map.clear();
        }

        public Builder add(String key, String value) {
            if (value != null)
                map.put(key, value);
            return this;
        }

        public HashMap<String, String> build() {
            return map;
        }

        /*public Builder addToken() {
            if(accessToken == null){
                Log.e(TAG,)
            }
            map.put("access_token", );
            return this;
        }*/
    }
}
