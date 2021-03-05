package edu.zc.oj.service;

import edu.zc.oj.entity.Config;
import edu.zc.oj.entity.Result;

/**
 * @author coderPlus-tr
 */
public interface JudgeClientService {

    /**
     * judge code
     * @param config edu.zc.oj.entity.Config
     * @return edu.zc.oj.entity.Result
     */
    Result run(Config config);
}
