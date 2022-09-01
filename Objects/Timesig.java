package Objects;

/**
 * The enum Objects.Timesig. Represents the possible time signatures of bagpipe tunes
 */
public enum Timesig {
    TWOTWO{
        @Override
        public String toString() {
            return "2/2";
        }
    }
    , TWOFOUR{
        @Override
        public String toString() {
            return "2/4";
        }
    }
    , THREEFOUR{
        @Override
        public String toString() {
            return "3/4";
        }
    }
    , FOURFOUR{
        @Override
        public String toString() {
            return "4/4";
        }
    }
    , SIXEIGHT{
        @Override
        public String toString() {
            return "6/8";
        }
    }
    , NINEEIGHT{
        @Override
        public String toString() {
            return "9/8";
        }
    }
    , TWELVEEIGHT{
        @Override
        public String toString() {
            return "12/8";
        }
    };

    /**
     * Convert String representation of time signature to enum value.
     *
     * @param ts the ts
     * @return the style
     */
    public static Timesig convert(String ts){
        return switch (ts) {
            case "2/2" -> Timesig.TWOTWO;
            case "2/4" -> Timesig.TWOFOUR;
            case "3/4" -> Timesig.THREEFOUR;
            case "4/4" -> Timesig.FOURFOUR;
            case "6/8" -> Timesig.SIXEIGHT;
            case "9/8" -> Timesig.NINEEIGHT;
            case "12/8" -> Timesig.TWELVEEIGHT;
            default -> null;
        };
    }

}
