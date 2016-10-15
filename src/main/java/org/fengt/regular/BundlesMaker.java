package org.fengt.regular;
import java.io.File;

public class BundlesMaker {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        String newPluginPath = "D:/tools/PyDev 2.7.3/plugins";
        String pluginPath = "D:/tools/MyEclipse/Common/plugins";
        File direct = new File(newPluginPath);
        File[] files = direct.listFiles();
        for(File f : files){
            String s = f.getName();
            int p = s.lastIndexOf("_");
            String name = s.substring(0, p);
            String version = s.substring(p+1);
            //.jar直接拼接，文件夹形式的后边加"/"
            if(f.isDirectory())
                s = s+"/";
            System.out.println(name+","+version+",file:/"+pluginPath+"/"+s+",4,false");
        }
    }
 
}
