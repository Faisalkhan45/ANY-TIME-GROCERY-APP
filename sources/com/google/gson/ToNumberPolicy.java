package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.math.BigDecimal;

public enum ToNumberPolicy implements ToNumberStrategy {
    DOUBLE {
        public Double readNumber(JsonReader in) throws IOException {
            return Double.valueOf(in.nextDouble());
        }
    },
    LAZILY_PARSED_NUMBER {
        public Number readNumber(JsonReader in) throws IOException {
            return new LazilyParsedNumber(in.nextString());
        }
    },
    LONG_OR_DOUBLE {
        /* Debug info: failed to restart local var, previous not found, register: 7 */
        public Number readNumber(JsonReader in) throws IOException, JsonParseException {
            String value = in.nextString();
            try {
                return Long.valueOf(Long.parseLong(value));
            } catch (NumberFormatException e) {
                Double d = Double.valueOf(value);
                if ((!d.isInfinite() && !d.isNaN()) || in.isLenient()) {
                    return d;
                }
                throw new MalformedJsonException("JSON forbids NaN and infinities: " + d + "; at path " + in.getPreviousPath());
            } catch (NumberFormatException doubleE) {
                throw new JsonParseException("Cannot parse " + value + "; at path " + in.getPreviousPath(), doubleE);
            }
        }
    },
    BIG_DECIMAL {
        public BigDecimal readNumber(JsonReader in) throws IOException {
            String value = in.nextString();
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                throw new JsonParseException("Cannot parse " + value + "; at path " + in.getPreviousPath(), e);
            }
        }
    }
}
