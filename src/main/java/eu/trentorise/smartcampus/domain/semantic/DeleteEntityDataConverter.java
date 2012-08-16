package eu.trentorise.smartcampus.domain.semantic;

import it.sayservice.platform.core.domain.actions.DataConverter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import eu.trentorise.smartcampus.services.semantic.data.message.Semantic.Result;

public class DeleteEntityDataConverter implements DataConverter {

	@Override
	public Serializable toMessage(Map<String, Object> parameters) {
		return new HashMap<String, Object>(parameters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object fromMessage(Serializable object) {
		List<ByteString> data = (List<ByteString>)object;
		try {
			Result result = Result.parseFrom(data.get(0));
			if (result.hasStatus()) return result.getStatus();
		} catch (InvalidProtocolBufferException e) {
			return false;
		}
		return false;
	}
}
