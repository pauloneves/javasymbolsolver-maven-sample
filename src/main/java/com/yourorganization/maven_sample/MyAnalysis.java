package com.yourorganization.maven_sample;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Some code that uses JavaSymbolSolver.
 */
public class MyAnalysis {

    public static void main(String[] args) throws IOException {
        // Set up a minimal type solver that only looks at the classes used to run this sample.
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver());

        // Configure JavaParser to use type resolution
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedTypeSolver);
        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);

        //Carrega arquivo
        String codigo = new String(Files.readAllBytes(
                Paths.get("D:/dev/IdeaProjects/SimpleAnnotation/src/main/java/com/paulo/annon/App.java")));

        // Parse some code
        CompilationUnit cu = JavaParser.parse(codigo);

        // Find all the calculations with two sides:
        System.out.println("Inicio");
        for (AnnotationExpr be : cu.findAll(AnnotationExpr.class)) {// Find out what type it has:
            //ResolvedType resolvedType = be.calculateResolvedType();
            // Show that it's "double" in every case:
            System.out.println(be.toString() + " is a: " + be.asAnnotationExpr());
        }
        System.out.println("Fim");
    }
}
