package org.coral.server.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public class BloomFilterService {
 
//    private BloomFilter<String> configuredFilter;
 
    private static final BloomFilter<String> filter = BloomFilter.create(new Funnel<String>() {
    	private static final long serialVersionUID = 1L;
        @Override
        public void funnel(String arg0, PrimitiveSink arg1) {
            arg1.putString(arg0, Charsets.UTF_8);
        }
    }, 1024*1024*32);
 
    public static void main(String[] args) {
    	filter.put("王八蛋");
    	filter.put("傻逼");
    	System.out.println(filter.mightContain("傻逼"));
    	System.out.println(filter.mightContain("傻,逼"));
	}
}
