package at.app.dalight.dalight;

/**
 * Created by Stephan on 23.01.2015.
 */
public class DaliCommands {

    public final static String OFF = "0x00";
    public final static String UP = "0x01";
    public final static String DOWN = "0x02";
    public final static String STEP_UP = "0x03";
    public final static String STEP_DOWN = "0x04";
    public final static String RECALL_MAX_LEVEL = "0x05";
    public final static String RECALL_MIN_LEVEL = "0x06";
    public final static String STEP_DOWN_AND_OFF = "0x07";
    public final static String ON_AND_STEP_UP = "0x08";
    public final static String ENABLE_DAPC_SEQUENCE = "0x09";
    public final static String GO_TO_LAST_ACTIVE_LEVEL = "0x0A";

    public final static String GO_TO_SCENE_0 = "0x10";
    public final static String GO_TO_SCENE_1 = "0x11";
    public final static String GO_TO_SCENE_2 = "0x12";
    public final static String GO_TO_SCENE_3 = "0x13";
    public final static String GO_TO_SCENE_4 = "0x14";
    public final static String GO_TO_SCENE_5 = "0x15";
    public final static String GO_TO_SCENE_6 = "0x16";
    public final static String GO_TO_SCENE_7 = "0x17";
    public final static String GO_TO_SCENE_8 = "0x18";
    public final static String GO_TO_SCENE_9 = "0x19";
    public final static String GO_TO_SCENE_10 = "0x1A";
    public final static String GO_TO_SCENE_11 = "0x1B";
    public final static String GO_TO_SCENE_12 = "0x1C";
    public final static String GO_TO_SCENE_13 = "0x1D";
    public final static String GO_TO_SCENE_14 = "0x1E";
    public final static String GO_TO_SCENE_15 = "0x1F";

    //Configuration Instructions
    public final static String RESET = "0x20";
    public final static String STORE_ACTUAL_LEVEL_IN_DTR0 = "0x21";
    public final static String SAVE_PERSISTENT_VARIABLES = "0x22";
    public final static String SET_OPERATING_MODE = "0x23";
    public final static String RESET_MEMORY_BANK = "0x24";
    public final static String IDENTIFY_DEVICE = "0x25";
    public final static String SET_MAX_LEVEL = "0x2A";
    public final static String SET_MIN_LEVEL = "0x2B";
    public final static String SET_SYSTEM_FAILURE_LEVEL = "0x2C";
    public final static String SET_POWER_ON_LEVEL = "0x2D";
    public final static String SET_FADE_TIME = "0x2E";
    public final static String SET_FADE_RATE = "0x2F";
    public final static String SET_EXTENDED_FADE_TIME = "0x30";

    public final static String SET_SCENE_0 = "0x40";
    public final static String SET_SCENE_1 = "0x41";
    public final static String SET_SCENE_2 = "0x42";
    public final static String SET_SCENE_3 = "0x43";
    public final static String SET_SCENE_4 = "0x44";
    public final static String SET_SCENE_5 = "0x45";
    public final static String SET_SCENE_6 = "0x46";
    public final static String SET_SCENE_7 = "0x47";
    public final static String SET_SCENE_8 = "0x48";
    public final static String SET_SCENE_9 = "0x49";
    public final static String SET_SCENE_10 = "0x4A";
    public final static String SET_SCENE_11 = "0x4B";
    public final static String SET_SCENE_12 = "0x4C";
    public final static String SET_SCENE_13 = "0x4D";
    public final static String SET_SCENE_14 = "0x4E";
    public final static String SET_SCENE_15 = "0x4F";

    public final static String REMOVE_FROM_SCENE_0 = "0x50";
    public final static String REMOVE_FROM_SCENE_1 = "0x51";
    public final static String REMOVE_FROM_SCENE_2 = "0x52";
    public final static String REMOVE_FROM_SCENE_3 = "0x53";
    public final static String REMOVE_FROM_SCENE_4 = "0x54";
    public final static String REMOVE_FROM_SCENE_5 = "0x55";
    public final static String REMOVE_FROM_SCENE_6 = "0x56";
    public final static String REMOVE_FROM_SCENE_7 = "0x57";
    public final static String REMOVE_FROM_SCENE_8 = "0x58";
    public final static String REMOVE_FROM_SCENE_9 = "0x59";
    public final static String REMOVE_FROM_SCENE_10 = "0x5A";
    public final static String REMOVE_FROM_SCENE_11 = "0x5B";
    public final static String REMOVE_FROM_SCENE_12 = "0x5C";
    public final static String REMOVE_FROM_SCENE_13 = "0x5D";
    public final static String REMOVE_FROM_SCENE_14 = "0x5E";
    public final static String REMOVE_FROM_SCENE_15 = "0x5F";

    public final static String ADD_TO_GROUP_0 = "0x60";
    public final static String ADD_TO_GROUP_1 = "0x61";
    public final static String ADD_TO_GROUP_2 = "0x62";
    public final static String ADD_TO_GROUP_3 = "0x63";
    public final static String ADD_TO_GROUP_4 = "0x64";
    public final static String ADD_TO_GROUP_5 = "0x65";
    public final static String ADD_TO_GROUP_6 = "0x66";
    public final static String ADD_TO_GROUP_7 = "0x67";
    public final static String ADD_TO_GROUP_8 = "0x68";
    public final static String ADD_TO_GROUP_9 = "0x69";
    public final static String ADD_TO_GROUP_10 = "0x6A";
    public final static String ADD_TO_GROUP_11 = "0x6B";
    public final static String ADD_TO_GROUP_12 = "0x6C";
    public final static String ADD_TO_GROUP_13 = "0x6D";
    public final static String ADD_TO_GROUP_14 = "0x6E";
    public final static String ADD_TO_GROUP_15 = "0x6F";

    public final static String REMOVE_FROM_GROUP_0 = "0x70";
    public final static String REMOVE_FROM_GROUP_1 = "0x71";
    public final static String REMOVE_FROM_GROUP_2 = "0x72";
    public final static String REMOVE_FROM_GROUP_3 = "0x73";
    public final static String REMOVE_FROM_GROUP_4 = "0x74";
    public final static String REMOVE_FROM_GROUP_5 = "0x75";
    public final static String REMOVE_FROM_GROUP_6 = "0x76";
    public final static String REMOVE_FROM_GROUP_7 = "0x77";
    public final static String REMOVE_FROM_GROUP_8 = "0x78";
    public final static String REMOVE_FROM_GROUP_9 = "0x79";
    public final static String REMOVE_FROM_GROUP_10 = "0x7A";
    public final static String REMOVE_FROM_GROUP_11 = "0x7B";
    public final static String REMOVE_FROM_GROUP_12 = "0x7C";
    public final static String REMOVE_FROM_GROUP_13 = "0x7D";
    public final static String REMOVE_FROM_GROUP_14 = "0x7E";

    public final static String SET_SHORT_ADDRESS = "0x80";
    public final static String ENABLE_WRITE_MEMORY = "0x81";

    //Queries
    public final static String QUERY_STATUS = "0x90";
    public final static String QUERY_CONTROL_GEAR_PRESET = "0x91";
    public final static String QUERY_LAMP_FAILURE = "0x92";
    public final static String QUERY_LAMP_POWER_ON = "0x93";
    public final static String QUERY_LIMIT_ERROR = "0x94";
    public final static String QUERY_RESET_STATE = "0x95";
    public final static String QUERY_MISSING_SHORT_ADDRESS = "0x96";
    public final static String QUERY_VERSION_NUMBER = "0x97";
    public final static String QUERY_CONTENT_DTR0 = "0x98";
    public final static String QUERY_DEVICE_TYPE = "0x99";
    public final static String QUERY_PHYSICAL_MINIMUM = "0x9A";
    public final static String QUERY_POWER_FAILURE = "0x9B";
    public final static String QUERY_CONTENT_DTR1 = "0x9C";
    public final static String QUERY_CONTENT_DTR2 = "0x9D";
    public final static String QUERY_OPERATING_MODE = "0x9E";
    public final static String QUERY_LIGHT_SOURCE_TYPE = "0x9F";

    public final static String QUERY_ACTUAL_LEVEL = "0xA0";
    public final static String QUERY_MAX_LEVEL = "0xA1";
    public final static String QUERY_MIN_LEVEL = "0xA2";
    public final static String QUERY_POWER_ON_LEVEL = "0xA3";
    public final static String QUERY_SYSTEM_FAILURE_LEVEL = "0xA4";
    public final static String QUERY_FADE_TIME_FADE_RATE = "0xA5";
    public final static String QUERY_MANUFACTURER_SPECIFIC_MODE = "";
    public final static String QUERY_NEXT_DEVICE_TYPE = "0xA7";
    public final static String QUERY_EXTENDED_FADE_TIME = "0xA8";
    public final static String QUERY_CONTROL_GEAR_FAILURE = "0xAA";

    public final static String QUERY_SCENE_LEVEL_0 = "0xB0";
    public final static String QUERY_SCENE_LEVEL_1 = "0xB1";
    public final static String QUERY_SCENE_LEVEL_2 = "0xB2";
    public final static String QUERY_SCENE_LEVEL_3 = "0xB3";
    public final static String QUERY_SCENE_LEVEL_4 = "0xB4";
    public final static String QUERY_SCENE_LEVEL_5 = "0xB5";
    public final static String QUERY_SCENE_LEVEL_6 = "0xB6";
    public final static String QUERY_SCENE_LEVEL_7 = "0xB7";
    public final static String QUERY_SCENE_LEVEL_8 = "0xB8";
    public final static String QUERY_SCENE_LEVEL_9 = "0xB9";
    public final static String QUERY_SCENE_LEVEL_10 = "0xBA";
    public final static String QUERY_SCENE_LEVEL_11 = "0xBB";
    public final static String QUERY_SCENE_LEVEL_12 = "0xBC";
    public final static String QUERY_SCENE_LEVEL_13 = "0xBD";
    public final static String QUERY_SCENE_LEVEL_14 = "0xBE";
    public final static String QUERY_SCENE_LEVEL_15 = "0xBF";

    public final static String QUERY_GROUPS_0_TO_7 = "0xC0";
    public final static String QUERY_GROUPS_8_TO_15 = "0xC1";
    public final static String QUERY_RANDOM_ADDRESS_H = "0xC2";
    public final static String QUERY_RANDOM_ADDRESS_M = "0xC3";
    public final static String QUERY_RANDOM_ADDRESS_L = "0xC4";
    public final static String READ_MEMORY_LOCATION = "0xC5";

    //Application Extended Commands
    public final static String QUERY_EXTENDED_VERSION_NUMBER = "0xFF";

    //Special Commands
    public final static String TERMINATE = "0xA1";
    public final static String DATA_TRANSFER_REGISTER_0_DTR0 = "0xA3";
    public final static String INITIALISE = "0xA5";
    public final static String RANDOMISE = "0xA7";
    public final static String COMPARE = "0xA9";
    public final static String WITHDRAW = "0xAB";
    public final static String PING = "0xAD";
    public final static String SEARCHADDRH = "0xB1";
    public final static String SEARCHADDRM = "0xB3";
    public final static String SEARCHADDRL = "0xB5";
    public final static String PROGRAM_SHORT_ADDRESS = "0xB7";
    public final static String VERIFY_SHORT_ADDRESS = "0xB9";
    public final static String QUERY_SHORT_ADDRESS = "0xBB";
    public final static String PHYSICAL_SELECTION = "0xBD";
    public final static String ENABLE_DEVICE_TYPE_X = "0xC1";
    public final static String DATA_TRANSFER_REGISTER_1_DTR1 = "0xC3";
    public final static String DATA_TRANSFER_REGISTER_2_DTR2 = "0xC5";
    public final static String WRITE_MEMORY_LOCATION = "0xC7";
    public final static String WRITE_MEMORY_LOCATION_NO_REPLY = "0xC9";
}