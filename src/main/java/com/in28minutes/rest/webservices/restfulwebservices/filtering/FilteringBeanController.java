package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class FilteringBeanController {

    @GetMapping(value = "/filtering")
    public MappingJacksonValue getFilteringBean() {
        FilteringBean filteringBean = new FilteringBean("value1", "value2", "value3");
        List<String> fields = Arrays.asList("field1","field2");
        return getMapper(filteringBean, fields);
    }

    @GetMapping(value = "/filtering-list")
    public MappingJacksonValue getFilteringBeanList() {
        FilteringBean bean1 = new FilteringBean("value1", "value2", "value3");
        FilteringBean bean2 = new FilteringBean("value4", "value5", "value6");
        List<FilteringBean> beanList= Arrays.asList(bean1,bean2);
        List<String> fields = Arrays.asList("field2","field3");
        return getMapper(beanList,fields);
    }

    private MappingJacksonValue getMapper(Object object, List<String> list) {
        MappingJacksonValue mapper = new MappingJacksonValue(object);
        Set<String> fields = new HashSet<>(list);
        FilterProvider filters = getFilteredFields(fields);
        mapper.setFilters(filters);
        return mapper;
    }

    public FilterProvider getFilteredFields(Set<String> fields){
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SampleBeanFilter", filter);
        return filters;
    }
}
