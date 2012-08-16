package eu.trentorise.smartcampus.domain.semantic;

import it.sayservice.platform.core.domain.actions.DataConverter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import eu.trentorise.smartcampus.services.semantic.data.message.Semantic.Result;

public class CreateSCEntityDataConverter implements DataConverter {

	@Override
	public Serializable toMessage(Map<String, Object> parameters) {
		HashMap<String,Object> tuple = new HashMap<String, Object>();
		tuple.put("data", ConverterHelper.toData(parameters).toByteArray());
		return tuple;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object fromMessage(Serializable object) {
		List<ByteString> data = (List<ByteString>)object;
		try {
			Result result = Result.parseFrom(data.get(0));
			if (result.hasId()) return result.getId();
		} catch (InvalidProtocolBufferException e) {
			return null;
		}
		return -1L;
	}
}
