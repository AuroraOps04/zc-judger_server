package edu.zc.oj.judge;

import edu.zc.oj.configuration.JudgeConfiguration;
import edu.zc.oj.exception.FileCreateException;
import edu.zc.oj.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */

public class JudgeResource implements AutoCloseable {
    private final String judgeDir;

    public JudgeResource(String judgeId, JudgeConfiguration configuration) {
        this.judgeDir  = configuration.getWorkDir() + File.separator + judgeId;
        // 生成临时文件夹
        final File file = new File(this.judgeDir);
        if(!file.mkdirs()){
            throw new FileCreateException(this.judgeDir);
        }
        // chown chmod
        FileUtils.setOwnerAndGroup(this.judgeDir, configuration.getJudgeUser(), configuration.getJudgeGroup());
        FileUtils.setPermission(this.judgeDir);
    }
    public String getJudgeDir(){ return judgeDir; }
    @Override
    public void close() throws Exception {
        Files.delete(Paths.get(this.judgeDir));
    }
}
