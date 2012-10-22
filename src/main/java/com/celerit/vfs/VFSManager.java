package com.celerit.vfs;

/**
 * Created with IntelliJ IDEA.
 * User: rafael
 * Date: 20-05-2012
 * Time: 3:17
 * To change this template use File | Settings | File Templates.
 */
public class VFSManager {

    private static VFSManager singleton;

    public static VFSManager getInstance(){
        if(singleton == null){
            singleton = new VFSManager();
        }
        return singleton;

    }

    public void loadApps(){}
}
