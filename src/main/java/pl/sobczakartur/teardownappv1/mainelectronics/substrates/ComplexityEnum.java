package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

public enum ComplexityEnum {
        SIMPLE(0.1),
        LOW(0.3),
        MEDIUM(0.4),
        HIGH(0.5);


        private final Double compl;

        ComplexityEnum(Double compl) {
            this.compl = compl;
        }

        public Double getCompl() {
            return compl;
        }
    }