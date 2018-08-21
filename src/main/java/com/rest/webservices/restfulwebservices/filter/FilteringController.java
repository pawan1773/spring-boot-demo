package com.rest.webservices.restfulwebservices.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// dynamic filtering: field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue someBean() {
		// note: annotate SomeBean with @JsonFilter
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);

		return mapping;
	}

	// dynamic filtering: field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> list = new ArrayList<>();
		list.add(new SomeBean("value1", "value2", "value3"));
		list.add(new SomeBean("valueA", "valueB", "valueC"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);

		return mapping;
	}
}
