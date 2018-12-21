package com.zgl.common.support;

import org.jetbrains.annotations.Nullable;

import javax.persistence.AttributeConverter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface Jsr310Converters {

    class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

        @Override
        @Nullable
        public Date convertToDatabaseColumn(LocalDateTime dateTime) {
            if (dateTime != null) {
                Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                return Date.from(instant);
            }
            return null;
        }

        @Override
        @Nullable
        public LocalDateTime convertToEntityAttribute(Date date) {
            return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }
    }

    class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

        @Override
        @Nullable
        public Date convertToDatabaseColumn(LocalDate localDate) {
            if (localDate != null) {
                Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                return Date.from(instant);
            }
            return null;
        }

        @Override
        @Nullable
        public LocalDate convertToEntityAttribute(Date date) {
            return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

}
