package org.coral.server.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class CollectionUtil
{

    /**
	 * 将集合map1和map2叠加到map
	 */
	public static Map<Integer, Integer> mergeMap(Map<Integer, Integer> map1, Map<Integer, Integer> map2)
	{
		Map<Integer, Integer> map = Maps.newHashMap();
		mergeMap(map1, map2, map);
		return map;
	}
	
	/**
	 * 将集合map1和map2叠加到map
	 */
	public static void mergeMap(Map<Integer, Integer> map1, Map<Integer, Integer> map2, Map<Integer, Integer> map)
	{
		if(map1!=null)
			map.putAll(map1);
		mergeToMap(map2, map);
	}
	
	/**
	 * 将集合map1叠加到map
	 */
	public static void mergeToMap(Map<Integer, Integer> map1, Map<Integer, Integer> map)
	{
		if(map1==null)
			return;
		for(Entry<Integer, Integer> entry : map1.entrySet())
		{
			int v = map.getOrDefault(entry.getKey(), 0);
			v += entry.getValue();
			map.put(entry.getKey(), v);
		}
	}

    public static boolean isEmpty(Collection<?> collection)
    {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?,?> map)
    {
        return map == null || map.isEmpty();
    }

    public static List<?> getEmptyList()
    {
        return Collections.emptyList();
    }

    public static Comparator<Object> entryComp = new Comparator<Object>() {

        public int compare(Entry<?, ?> o1, Entry<?, ?> o2)
        {
            return ((String)o1.getKey()).compareTo((String)o2.getKey());
        }

		@Override
		public int compare(Object o1, Object o2) {
			return compare((Entry<?, ?>)o1, (Entry<?, ?>)o2);
		}

    };

}
