package com.celerit.servlet.ServletRegistration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rafael
 * Date: 20-05-2012
 * Time: 3:14
 * To change this template use File | Settings | File Templates.
 */
public class Dynamic implements ServletRegistration.Dynamic {
    @Override
    public void setLoadOnStartup(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<String> setServletSecurity(ServletSecurityElement servletSecurityElement) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfigElement) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRunAsRole(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAsyncSupported(boolean b) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<String> addMapping(String... strings) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<String> getMappings() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getRunAsRole() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getClassName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean setInitParameter(String s, String s1) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getInitParameter(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> stringStringMap) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, String> getInitParameters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
