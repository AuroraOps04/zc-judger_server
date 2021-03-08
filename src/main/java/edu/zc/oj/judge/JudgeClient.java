package edu.zc.oj.judge;

import edu.zc.oj.entity.Config;
import edu.zc.oj.entity.Result;

import java.util.concurrent.Future;

/**
 * @author coderPlus-tr
 */
public interface JudgeClient {
    Integer UNLIMITED = -1;

    /**
     * judge code
     *
     * @param config edu.zc.oj.entity.Config
     * @return edu.zc.oj.entity.Result
     */
    Result run(Config config);
}
