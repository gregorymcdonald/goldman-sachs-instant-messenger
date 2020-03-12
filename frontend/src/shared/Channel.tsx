import { Message } from "./Message";

export interface Channel {
    identifier: string;
    members: string[];
    messages?: Message[];
}