
package com.test.training.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value(staticConstructor = "of")
@Builder
@JsonDeserialize(builder = GenericContainer.GenericContainerBuilder.class)
@ToString(includeFieldNames = true)
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "response", "errors" })
public class GenericContainer<T> {

	final private T response;

	final private String error;

	public GenericContainer(final T response, final String errors) {
		this.response = response;
		this.error = errors;
	}

	public static <T> GenericContainer<T> of(final T response, final String errors) {
		return new GenericContainer<T>(response, errors);
	}

	public static <T> GenericContainer<T> of(final T response) {
		return of(response, null);
	}

	public static <T> GenericContainer<T> of(final String errors) {
		return of(null, errors);
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static final class GenericContainerBuilder<T> {
	}
}
