/**
 * The enum Style. represents the possible bagpipe tune styles
 */
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
    }
}
