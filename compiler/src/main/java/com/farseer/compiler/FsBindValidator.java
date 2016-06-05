/*
 *    Copyright 2016 ifarseer
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
 */

package com.farseer.compiler;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.List;

import static javax.lang.model.util.ElementFilter.methodsIn;
/**
 * class description
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 16/6/5
 */
public final class FsBindValidator {
    ValidationReport<Element> validate(Element element) {
        ValidationReport.Builder<Element> builder = ValidationReport.about(element);
        List<ExecutableElement> members = methodsIn(((TypeElement) element).getEnclosedElements());
        // TODO
        return builder.build();
    }
}
