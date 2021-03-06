/*
 * Copyright 2019 This source file is part of the Máni open source project
 *
 * Copyright (c) 2018 - 2019.
 *
 * Licensed under Mozilla Public License 2.0
 *
 * See https://github.com/mani-language/Mani/blob/master/LICENSE.md for license information.
 */

package com.mani.lang.Modules.math;

import com.mani.lang.core.Interpreter;
import com.mani.lang.domain.ManiCallable;
import com.mani.lang.Modules.Module;

import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

public class math implements Module {

    private static final DoubleFunction<Double> doubleToNumber = Double::valueOf;

    @Override
    public void init(Interpreter interpreter) {
        // Defining common Math variables.
        interpreter.define("PI", Math.PI);
        interpreter.define("E", Math.E);

        // Defining the functions.
        interpreter.addSTD("rand", new math_rand());
        interpreter.addSTD("acos", functionConvert(Math::acos));
        interpreter.addSTD("asin", functionConvert(Math::asin));
        interpreter.addSTD("atan", functionConvert(Math::atan));
        interpreter.addSTD("atan2", biFunctionConvert(Math::atan2));
        interpreter.addSTD("cbrt", functionConvert(Math::cbrt));
        interpreter.addSTD("ceil", functionConvert(Math::ceil));
        interpreter.addSTD("cos", functionConvert(Math::cos));
        interpreter.addSTD("sin", functionConvert(Math::sin));
        interpreter.addSTD("tan", functionConvert(Math::tan));
        interpreter.addSTD("cosh", functionConvert(Math::cosh));
        interpreter.addSTD("exp", functionConvert(Math::exp));
        interpreter.addSTD("expm1", functionConvert(Math::expm1));
        interpreter.addSTD("floor", functionConvert(Math::floor));
        interpreter.addSTD("round", new ManiCallable() {
            @Override
            public int arity() { return 1; }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                return Math.round((Double) arguments.get(0));
            }
        });
    }

    private static ManiCallable functionConvert(DoubleUnaryOperator op) {
        return new ManiCallable() {
            @Override
            public int arity() { return 1; }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                return doubleToNumber.apply(op.applyAsDouble((double) arguments.get(0)));
            }
        };
    }

    private static ManiCallable biFunctionConvert(DoubleBinaryOperator op) {
        return new ManiCallable() {
            @Override
            public int arity() { return 2; }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                return doubleToNumber.apply(op.applyAsDouble((double) arguments.get(0), (double) arguments.get(1)));
            }
        };
    }

    @Override
    public boolean hasExtensions() {
        return false;
    }

    @Override
    public Object extensions() {
        return null;
    }
}
