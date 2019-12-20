/**
 * Copyright 2018 Mike Hummel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mhus.lib.vaadin.widgets.converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class StringConverter implements Converter<String, String> {

	private static final long serialVersionUID = 1L;

    @Override
    public Result<String> convertToModel(String value, ValueContext context) {
        return new SimpleResult<String>(value, null);
    }

    @Override
    public String convertToPresentation(String value, ValueContext context) {
        if (value == null) return "";
        return String.valueOf(value);
    }

}
