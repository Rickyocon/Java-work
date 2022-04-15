public interface Tree<T>
{
    boolean empty();
    int height();  // height of tree
    T search(T x); // search for something .compareTo(x)==0
    Tree<T> insert(T x); // insert into tree, usages: t = t.insert(x);
    Tree<T> delete(T x); // search and delete x from tree, t=t.delete(x);
    int size();

    // other methods (skeletons provided so program will compile without them)
    default Tree<T> makeclone() {throw new Error("not implemented");}
    default T leastitem() {throw new Error("not implemented");}
    default int width(int hdistance) {throw new Error("not implemented");}
    default boolean isBst(T min, T max) {throw new Error("not implemented");}
    default void map_inorder(java.util.function.Consumer<? super T> f)
    {throw new Error("not implemented");}        
}// Tree interace, designed to have two subclasses (nil and vertex)

/*
   Recent versions of Java have added the ability to not only declare
   functions but to also provide default implementations.  This is 
   just a convenience.  It somewhat obscures the distinction between an
   interface and an abstract class, but unlike a class, you can't have
   instance variables and you can't refer to 'this'.  

   This feature should not distract from your understanding of interfaces
   as purely abstract declarations.

   I only used this feature here so that my code will compile without
   implementing these functions: you will provide more interesting implementations
   these functions in your program.
*/
