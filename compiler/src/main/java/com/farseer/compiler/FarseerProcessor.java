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


import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;

import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;

/**
 * class description
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 16/6/5
 */

@AutoService(Processor.class)
public class FarseerProcessor extends BasicAnnotationProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    protected Iterable<? extends ProcessingStep> initSteps() {
        Messager messager = processingEnv.getMessager();
        FsComponentValidator fsBindValidator = new FsComponentValidator();
        FsComponentGenerator fsBindGenerator = new FsComponentGenerator(processingEnv);

        return ImmutableList.of(
                new FsComponentProcessingStep(messager, fsBindValidator, fsBindGenerator)
        );
    }
}
