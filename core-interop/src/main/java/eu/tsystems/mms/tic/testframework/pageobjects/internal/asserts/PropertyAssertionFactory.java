package eu.tsystems.mms.tic.testframework.pageobjects.internal.asserts;

import java.io.File;

public interface PropertyAssertionFactory {
    <T> IBinaryPropertyAssertion<T> binary(PropertyAssertion parent, AssertionProvider<T> provider);
    <T> IQuantifiedPropertyAssertion<T> quantified(PropertyAssertion parent, AssertionProvider<T> provider);
    <T> IStringPropertyAssertion<T> string(PropertyAssertion parent, AssertionProvider<T> provider);
    IFilePropertyAssertion file(PropertyAssertion parent, AssertionProvider<File> provider);
    IImagePropertyAssertion image(PropertyAssertion parent, AssertionProvider<File> provider);
    default <T> IBinaryPropertyAssertion<T> binary(AssertionProvider<T> provider) {
        return binary(null, provider);
    }
    default <T> IQuantifiedPropertyAssertion<T> quantified(AssertionProvider<T> provider) {
        return quantified(null, provider);
    }
    default <T> IStringPropertyAssertion<T> string(AssertionProvider<T> provider) {
        return string(null, provider);
    }
    default IFilePropertyAssertion file(AssertionProvider<File> provider) {
        return file(null, provider);
    }
    default IImagePropertyAssertion image(AssertionProvider<File> provider) {
        return image(null, provider);
    }
}
