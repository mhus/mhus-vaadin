/**
 * Copyright (C) 2019 Mike Hummel (mh@mhus.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mhus.osgi.vaadinkarafbridge.impl;

import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import de.mhus.osgi.api.karaf.AbstractCmd;
import de.mhus.osgi.vaadinbridge.BundleWatch;

@Command(
        scope = "vaadin",
        name = "watch-refresh",
        description = "Full-Refresh automatic bundle watch")
@Service
public class CmdWatchRefresh extends AbstractCmd {

    @Reference private BundleWatch watch;

    @Override
    public Object execute2() throws Exception {

        watch.refreshAll();

        return null;
    }
}
