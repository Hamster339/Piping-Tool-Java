package Objects;

/**
 * The enum Objects.Style. represents the possible bagpipe tune styles
 */
@SuppressWarnings("SpellCheckingInspection")
public enum Style {
    MARCH{
        @Override
        public String toString() {
            return "March";
        }
    }
    ,STRATHSPAY{
        @Override
        public String toString() {
            return "Strathspay";
        }
    }
    ,REEL{
        @Override
        public String toString() {
            return "Reel";
        }
    }
    ,WALTZ{
        @Override
        public String toString() {
            return "Waltz";
        }
    }
    ,JIG{
        @Override
        public String toString() {
            return "Jig";
        }
    }
    ,SLOWAIR{
        @Override
        public String toString() {
            return "Slow Air";
        }
    }
    ,HORNPIPE{
        @Override
        public String toString() {
            return "Hornpipe";
        }
    }
    ,PIBROCH{
        @Override
        public String toString() {
            return "Pibroch";
        }
    }
    ,OTHER{
        @Override
        public String toString() {
            return "Other";
        }
    };

    /**
     * Convert String representation of style to enum value.
     *
     * @param ts the ts
     * @return the style
     */
    public static Style convert(String ts){
        return switch (ts) {
            case "March" -> Style.MARCH;
            case "Strathspay" -> Style.STRATHSPAY;
            case "Reel" -> Style.REEL;
            case "Waltz" -> Style.WALTZ;
            case "Jig" -> Style.JIG;
            case "Slow Air" -> Style.SLOWAIR;
            case "Hornpipe" -> Style.HORNPIPE;
            case "Pibroch" -> Style.PIBROCH;
            case "Other" -> Style.OTHER;
            default -> null;
        };
    }

}
