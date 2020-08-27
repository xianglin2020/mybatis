package person.xianglin.mybatisspringboot.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @author xianglin
 */
public class MapperNameGenerator implements BeanNameGenerator {
    private Map<String, Integer> nameMap = new HashMap<>();

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        String shortName = ClassUtils.getShortName(Objects.requireNonNull(beanDefinition.getBeanClassName()));
        String decapitalize = Introspector.decapitalize(shortName);
        if (nameMap.containsKey(decapitalize)) {
            Integer integer = nameMap.get(decapitalize);
            int newInteger = integer + 1;
            nameMap.put(decapitalize, newInteger);

            decapitalize += newInteger;
        } else {
            nameMap.put(decapitalize, 1);
        }
        return null;
    }
}
