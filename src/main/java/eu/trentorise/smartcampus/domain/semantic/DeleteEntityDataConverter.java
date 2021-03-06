/*******************************************************************************
 * Copyright 2012-2013 Trento RISE
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/
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
