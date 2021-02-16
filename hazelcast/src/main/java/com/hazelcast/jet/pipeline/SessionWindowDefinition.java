/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.jet.pipeline;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;

/**
 * Represents the definition of a session window.
 *
 * @since 3.0
 */
public class SessionWindowDefinition extends WindowDefinition {

    private long sessionTimeout;

    SessionWindowDefinition() {
    }

    SessionWindowDefinition(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    @Override
    public SessionWindowDefinition setEarlyResultsPeriod(long earlyResultPeriodMs) {
        return (SessionWindowDefinition) super.setEarlyResultsPeriod(earlyResultPeriodMs);
    }

    /**
     * Returns the session timeout, which is the largest difference in the
     * timestamps of any two consecutive events in the session window.
     */
    public long sessionTimeout() {
        return sessionTimeout;
    }

    @Override
    public int getClassId() {
        return JetPipelineDataSerializerHook.SESSION_WINDOW_DEFINITION;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        super.writeData(out);
        out.writeLong(sessionTimeout);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        super.readData(in);
        sessionTimeout = in.readLong();
    }
}
