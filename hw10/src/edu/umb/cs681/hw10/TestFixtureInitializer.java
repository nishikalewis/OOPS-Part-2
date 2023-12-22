package edu.umb.cs681.hw10;

import com.sun.source.tree.DirectiveTree;

import java.time.LocalDateTime;

public class TestFixtureInitializer {
    static LocalDateTime time=LocalDateTime.now();

    public static FileSystem createFS(){
        FileSystem fileSystem=FileSystem.getFileSystem();
        Directory prjRoot=new Directory(null,"prjRoot",0,time);
        fileSystem.appendRoot(prjRoot);
        Directory src=new Directory(prjRoot,"src",0,time);
        Directory lib=new Directory(prjRoot,"lib",0,time);
        Directory test=new Directory(prjRoot,"test",0,time);
        Directory src1=new Directory(test,"src",0,time);
        File a=new File(src,"a",10,time);
        File b=new File(src,"b",15,time);
        File c=new File(lib,"c",20,time);
        File x=new File(prjRoot,"x",5,time);
        File d=new File(src1,"d",10,time);
        Link y=new Link(prjRoot,"y",0,time,src1);
        return fileSystem;
    }
}