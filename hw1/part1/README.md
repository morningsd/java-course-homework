## Ordinary application

### Compilation and start of the program

![compile-run](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/compile-run.png?raw=true)

### Building the jar archive

![make-jar](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/make-jar.png?raw=true)

## Application with third party library

### Compilation and start of the program

![compile-run-third-party-library](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/compile-run-third-party-library.png?raw=true)

### Building the jar archive

![make-jar-third-party-library](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/make-jar-third-party-library.png?raw=true)

## Application with all classes types (inner, nested, local, anonymous)

![compile-run-all-classes-types-1](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/compile-run-all-classes-types-1.png?raw=true)

![compile-run-all-classes-types-2](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/compile-run-all-classes-types-2.png?raw=true)

As we can see, java compiler output contains 6 classes:
 - AllClassesTypes$1.class
 - AllClassesTypes$1Local.class
 - AllClassesTypes$Inner.class
 - AllClassesTypes$Nested.class
 - AllClassesTypes.class
 - LocalInterface.class

$ symbol is used to represent inner classes, so 
 - anonymous class => $1
 - local class     => $1Local
 - inner class     => $Inner
 - nested class    => $Nested

## Application with anonymous class and lambda that implements the same interface

![compile-run-anonymous-lambda-types-1](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/compile-run-anonymous-lambda-types-1.png?raw=true)

![compile-run-anonymous-lambda-types-2](https://github.com/dem14n/java-course-homework/blob/master/hw1/part1/images/compile-run-anonymous-lambda-types-2.png?raw=true)

So the result shows us that lambdas don't compile as anonymous classes.

If JVM would desugar lambdas into anonymous classes during compile time, that wouldn't make them true functions and lead to expensive process of initiation of new class whenever it encounters lambdas. Because of that 
> lambda syntax is desugared into JVM level instructions generated during compilation, that means that the actual responsibility of constructing lambda is deferred to runtime.


