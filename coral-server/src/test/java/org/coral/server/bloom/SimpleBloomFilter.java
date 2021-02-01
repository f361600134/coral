package org.coral.server.bloom;
import java.util.BitSet;

/**
 * Created by haicheng.lhc on 18/05/2017.
 *
 * @author haicheng.lhc
 * @date 2017/05/18
 */
public class SimpleBloomFilter {

    private static final int DEFAULT_SIZE = 2 << 24;
    private static final int[] seeds = new int[] {7, 11, 13, 31, 37, 61,};

    private BitSet bits = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] func = new SimpleHash[seeds.length];

    /**
     * 	简易布隆过滤器构造
     */
    public SimpleBloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    /**
     * 	添加一个数据, 通过
     * @param value
     */
    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 	简易hash结构
     * @author Administrator
     *
     */
    public static class SimpleHash {

        private int cap;//数组长度
        private int seed;//种子

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 	把数据每一位根据seed计算hash值, 返回最终的hash
         * @param value
         * @return
         */
        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }

    }
    
    public static void main(String[] args) {
        String value = "fsc你好";
        SimpleBloomFilter filter = new SimpleBloomFilter();
        filter.add("1");
        filter.add("2");
        filter.add("3");
        filter.add("fsc");
        filter.add("tiny");
        filter.add("jeremy");
        System.out.println(filter.contains(value));
        filter.add(value);
        System.out.println(filter.contains(value));
    }
}
