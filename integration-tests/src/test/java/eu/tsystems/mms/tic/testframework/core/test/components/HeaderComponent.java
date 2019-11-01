package eu.tsystems.mms.tic.testframework.core.test.components;

import eu.tsystems.mms.tic.testframework.pageobjects.Component;
import eu.tsystems.mms.tic.testframework.pageobjects.IGuiElement;

public class HeaderComponent extends Component<HeaderComponent> {

    public HeaderComponent(IGuiElement rootElement) {
        super(rootElement);
    }

    @Override
    protected HeaderComponent self() {
        return this;
    }
}