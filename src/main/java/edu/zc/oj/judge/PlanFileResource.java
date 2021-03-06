package edu.zc.oj.judge;

import edu.zc.oj.configuration.JudgeConfiguration;
import edu.zc.oj.exception.FileCreateException;
import edu.zc.oj.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */
@Slf4j
public class PlanFileResource implements AutoCloseable {
    private final String filePath;
    public PlanFileResource(JudgeConfiguration configuration, String filePath) throws IOException, FileCreateException{
        this(configuration, filePath, null);
    }
    public PlanFileResource(JudgeConfiguration configuration, String filePath, String content) throws IOException, FileCreateException {
        this.filePath = filePath;
        if (!new File(filePath).createNewFile()) {
            throw new FileCreateException("SrcFileResource: create file " + filePath + "reject");
        }
        if(null != content){
            Files.write(Paths.get(filePath), content.getBytes());
        }
        FileUtils.setOwnerAndGroup(filePath, configuration.getJudgeUser(), configuration.getJudgeGroup());
        FileUtils.setPermission(filePath);

    }

    @Override
    public void close() throws Exception {
        Files.delete(Paths.get(this.filePath));
    }
}
