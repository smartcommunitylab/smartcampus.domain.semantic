package eu.trentorise.smartcampus.domain.semantic;

import java.util.Arrays;
import java.util.Map;

import eu.trentorise.smartcampus.services.semantic.data.message.Semantic.Data;

public class ConverterHelper {

	public static Data toData(Map<String, Object> parameters) {
		Data.Builder data = Data.newBuilder();
		if (parameters.get("type") != null) data.setType((String)parameters.get("type"));
		if (parameters.get("name") != null) data.setName((String)parameters.get("name"));
		if (parameters.get("description") != null) data.setDescription((String)parameters.get("description"));
		if (parameters.get("tags") != null) {
			for (Tag tag : (Tag[])parameters.get("tags")) {
				eu.trentorise.smartcampus.services.semantic.data.message.Semantic.Tag.Builder tagBuilder = 
						eu.trentorise.smartcampus.services.semantic.data.message.Semantic.Tag.newBuilder();
				if (tag.getId() != null) tagBuilder.setId(tag.getId());
				else tagBuilder.setId(-1L);
				if (tag.getDescription() != null) tagBuilder.setDescription(tag.getDescription());
				if (tag.getName() != null) tagBuilder.setName(tag.getName());
				if (tag.getSummary() != null) tagBuilder.setSummary(tag.getSummary());
				data.addTag(tagBuilder.build());
			}
		} 
		if (parameters.get("relations") != null) {
			data.addAllRelation(Arrays.asList((Long[])parameters.get("relations")));
		}
		return data.build();
	}
}
