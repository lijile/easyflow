package org.lecoder.easyflow.modules.core.toolkit;

import cn.hutool.core.util.ObjectUtil;
import org.lecoder.easyflow.modules.core.entity.FlowVariable;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 变量类型工具类
 *
 * @author: lijile
 * @date: 2021/10/26 9:34
 * @version: 1.0
 */
public class VariableTypeHelper {

    public static List<FlowVariable> getVariables(Map<String, Object> variables) {
        if (variables == null) {
            return Collections.EMPTY_LIST;
        }
        List<FlowVariable> variableList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            FlowVariable variable = new FlowVariable();
            if (ObjectUtil.isBasicType(value) || value instanceof BigDecimal) {
                variable.setType(value.getClass().getName());
            } else if(value instanceof Collection) {
                variable.setType(Collection.class.getName());
                variable.setValuee(join((Collection) value));
            } else {
                variable.setType(Object.class.getName());
            }
            variable.setKeyy(key);
            if (variable.getValuee() == null) {
                variable.setValuee(value.toString());
            }
            variableList.add(variable);
        }
        return variableList;
    }

    private static String join(Collection value) {
        Set<?> set = new HashSet(value);
        return set.stream().map(Object::toString).collect(Collectors.joining(","));
    }

}
