This is a repository containing use cases and examples of choreographies written in the [Choral language](https://choral-lang.org).

# Installation

The project requires maven and JDK 17.

1. Make sure you have the Choral libraries installed (=org.choral-lang:choral=
   and =org.choral-lang:runtime=). To do this, clone the 
   [[https://github.com/choral-lang/choral][Choral repository]] and build and 
   install the libraries:

   #+BEGIN_EXAMPLE
     mvn install
   #+END_EXAMPLE

2. Make sure you have the =choral= compiler script on your =PATH= and that
   you've set =CHORAL_PATH=.

   The script is a wrapper that looks for the Choral standalone JAR under
   =CHORAL_HOME= and invokes it. The version of the JAR should match the version
   of the runtime you've installed.

   When building Choral from source, You can find the JAR under Choral's
   =dist/target=, which you can use as your =CHORAL_PATH=.

3. To build this project, run the following command in the root directory:

   #+BEGIN_EXAMPLE
     mvn compile
   #+END_EXAMPLE


# Adding examples

You can add a new example by creating a directory in `src/main/choral/examples`.
Let's say the main Choral class you're compiling is called `MyExample`.

You'll need to update `pom.xml` to compile your example. Look for `exec-maven-plugin`---it
looks something like this:

```
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.0.0</version>
    <executions>
        <!-- insert executions here ...  -->
    </executions>
</plugin>
```

In the `<executions>` field, add a new execution for your example. Use the following
template, updating the `<id>` field and the last `<argument>` field.

```
<execution>
    <id>choral-epp-bipair</id>       <!-- UPDATE THIS ID -->
    <phase>generate-sources</phase>
    <goals>
        <goal>exec</goal>
    </goals>
    <configuration>
        <executable>choral</executable>
        <arguments>
            <argument>epp</argument>
            <argument>--sources=${project.basedir}/src/main/choral</argument>
            <argument>--headers=${project.basedir}/src/main/choral</argument>
            <argument>--target=${project.build.directory}/generated-sources/choral</argument>
            <argument>BiPair</argument> <!-- UPDATE THIS CLASS NAME -->
        </arguments>
    </configuration>
</execution>
```

If your example is called `MyExample`, this means you replace `choral-epp-bipair` with
something like `choral-epp-myexample`, and you replace `<argument>BiPair</argument>` with 
`<argument>MyExample</argument>`. If you did it correctly, your Choral code will compile
when you run `mvn compile`.

To use your Choral code in Java, add a new directory to the `src/main/java/choral/examples`
directory. Use the `diffie-hellman` example as a reference.