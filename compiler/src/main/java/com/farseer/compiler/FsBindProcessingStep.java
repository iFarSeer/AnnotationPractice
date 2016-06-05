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

import com.farseer.FsBind;
import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * class description
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 16/6/5
 */
public class FsBindProcessingStep implements BasicAnnotationProcessor.ProcessingStep {

    private final Messager messager;
    private final FsBindValidator fsBindValidator;
    private final FsBindGenerator fsBindGenerator;

    public FsBindProcessingStep(Messager messager, FsBindValidator fsBindValidator, FsBindGenerator fsBindGenerator) {
        this.messager = messager;
        this.fsBindValidator = fsBindValidator;
        this.fsBindGenerator = fsBindGenerator;
    }

    @Override
    public Set<? extends Class<? extends Annotation>> annotations() {
        return ImmutableSet.<Class<? extends Annotation>>of(FsBind.class);
    }

    @Override
    public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
        for (Element element : elementsByAnnotation.get(FsBind.class)) {
            ValidationReport<Element> validationReport = fsBindValidator.validate(element);
            validationReport.printMessagesTo(messager);

            if (validationReport.isClean()) {
                fsBindGenerator.write(element);

            }
        }
        return ImmutableSet.of();
    }
}
