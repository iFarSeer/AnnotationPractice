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
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.tools.Diagnostic;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * class description
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 16/6/5
 */
public class FsBindGenerator {
    private final ProcessingEnvironment processingEnv;

    public FsBindGenerator(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    public void write(Element element){

        FsBind fsBind = element.getAnnotation(FsBind.class);
        checkNotNull(fsBind.packageName());
        checkNotNull(fsBind.className());
        String packageName = fsBind.packageName();
        String className = fsBind.className();

        if (element.getKind() != ElementKind.CLASS) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "only support class");
        }
        MethodSpec main = MethodSpec.methodBuilder("log")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(CharSequence.class, "tag")
                .addParameter(CharSequence.class, "args")
                .addStatement("$T.out.println(args)", System.class)
                .build();


        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();
        JavaFile javaFile = JavaFile.builder(packageName, helloWorld).build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
