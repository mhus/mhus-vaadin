/**
 * Copyright 2018 Mike Hummel
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mhus.lib.vaadin.form;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import de.mhus.lib.core.MActivator;
import de.mhus.lib.core.M;
import de.mhus.lib.form.ActivatorAdapterProvider;
import de.mhus.lib.form.MForm;
import de.mhus.lib.form.MutableMForm;

public class VaadinForm extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public enum SHOW {
        MODEL,
        YES,
        NO
    };

    protected VaadinFormBuilder builder;
    protected MutableMForm form;
    protected SHOW showInformation = SHOW.MODEL;
    protected VaadinUiInformation informationPane;
    protected UiLayout layout;
    protected Panel formPanel;

    public VaadinForm() {}

    public VaadinForm(MutableMForm form) {
        setForm(form);
    }

    public void doBuild(MActivator activator) throws Exception {
        if (activator != null) form.setAdapterProvider(new ActivatorAdapterProvider(activator));
        doBuild();
    }

    public void doBuild() throws Exception {

        if (form.getAdapterProvider() == null)
            form.setAdapterProvider(
                    M.l(ActivatorAdapterProvider.class, DefaultAdapterProvider.class));

        if (isShowInformation()) {
            doBuildInformationPane();
        }
        if (builder == null) builder = new VaadinFormBuilder();

        builder.setForm(form);
        builder.doBuild();
        builder.doRevert();

        formPanel = new Panel();
        formPanel.setWidth("100%");
        formPanel.setHeight("100%");

        layout = builder.getLayout();
        formPanel.setContent(layout.getComponent());

        addComponent(formPanel);
        setExpandRatio(formPanel, 1);
    }

    protected void doBuildInformationPane() {
        informationPane = new VaadinUiInformation();
        form.setInformationPane(informationPane);
        addComponent(informationPane);
        setExpandRatio(informationPane, 0);
        int h = form.getModel().getInt("showInformationHeight", 0);
        informationPane.setHeight(h > 0 ? h + "px" : "100px");
        informationPane.setWidth("100%");
    }

    public boolean isShowInformation() {
        return showInformation == SHOW.YES
                || showInformation == SHOW.MODEL
                        && form != null
                        && form.getModel() != null
                        && form.getModel().getBoolean("showInformation", false);
    }

    public void setShowInformation(boolean showInformation) {
        this.showInformation = showInformation ? SHOW.YES : SHOW.NO;
    }

    public void setShowInformation(SHOW showInformation) {
        this.showInformation = showInformation;
    }

    public SHOW getShowInformation() {
        return showInformation;
    }

    public VaadinFormBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(VaadinFormBuilder builder) {
        this.builder = builder;
    }

    public MForm getForm() {
        return form;
    }

    public void setForm(MutableMForm form) {
        this.form = form;
    }
}
