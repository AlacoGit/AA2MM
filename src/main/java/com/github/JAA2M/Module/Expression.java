package com.github.JAA2M.Module;

import com.github.JAA2M.wString;

import java.util.List;

public record Expression(/*DWORD*/ int id, Category category, String name, String interactiveName, String description, List<Value.Types> parameters, Value.Types returnType) {


    public static final Expression[][] g_Expressions = new Expression[][]{
            {// INVALID
                    new Expression(ExpressionTypes.CONSTANT.id, ExpressionCategories.GENERAL, "Constant", "Constant", "An arbitrary constant to input", List.of(), Value.Types.INVALID),
                    new Expression(ExpressionTypes.VAR.id, ExpressionCategories.GENERAL, "Variable", "Variable", "A Variable", List.of(), Value.Types.INVALID),
                    new Expression(ExpressionTypes.NAMEDCONSTANT.id, ExpressionCategories.GENERAL, "Enumeration", "Enumeration", "A known constant with a name", List.of(), Value.Types.INVALID)
            },
            { //INT
                    new Expression(ExpressionTypes.CONSTANT.id, ExpressionCategories.GENERAL,"Constant", "Constant", "An arbitrary constant to input", List.of(), Value.Types.INT),
                    new Expression(ExpressionTypes.VAR.id, ExpressionCategories.GENERAL,"Variable", "Variable", "A Variable", List.of(), Value.Types.INT),
                    new Expression(ExpressionTypes.NAMEDCONSTANT.id, ExpressionCategories.GENERAL, "Enumeration", "Enumeration", "A known constant with a name", List.of(), Value.Types.INT),
                    new Expression(4, ExpressionCategories.MATH, "Random Int", "RandomInt(min: %p, max: %p)", "Generates a random integer between the two arguments (including both)", List.of(Value.Types.INT,Value.Types.INT), Value.Types.INT),
                    new Expression(ExpressionTypes.INT_PLUS.id, ExpressionCategories.MATH, ("+"), "(%p + %p)", "Adds two integers", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(6, ExpressionCategories.MATH, "-", "(%p - %p)", "Substracts two integers", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(7, ExpressionCategories.MATH, "/", "(%p / %p)", "Divide two integers", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(8, ExpressionCategories.MATH, "*", "(%p * %p)", "Multiply two integers", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(9, ExpressionCategories.EVENT, "Triggering Card", "TriggerCard", "The card that triggered the event that caused the trigger to run. \n Used by many events.", List.of(), Value.Types.INT),
                    new Expression(10, ExpressionCategories.EVENT, "This Card", "ThisCard", "The card to whom this trigger belongs to", List.of(), Value.Types.INT),
                    new Expression(11, ExpressionCategories.EVENT, "Npc Answer - Target", "AnswerTarget", "In a NPC Answered event, the character the NPC answered to.", List.of(), Value.Types.INT),
                    new Expression(12, ExpressionCategories.EVENT, "Npc Answer - Conversation ID", "ConversationId", "The Type of Question the NPC answered in a NPC Answered event.", List.of(), Value.Types.INT),
                    new Expression(13, ExpressionCategories.EVENT, "Npc Room Target", "NPCRoomTarget", "Room that the Npc Walks to in a Npc Walks to Room event.", List.of(), Value.Types.INT),
                    new Expression(14, ExpressionCategories.EVENT, "Npc Action", "ActionId", "The Type of Action an Npc Wants to Perform in a no-target-action event, or the conversation, or the conversation within conversation end event. id in the targeted actions.", List.of(), Value.Types.INT),
                    new Expression(15, ExpressionCategories.EVENT, "Npc Talk Target", "TalkTarget", "In a Npc Talk With, or Npc Talk With About, or Conversation End event, this is the character the Npc talks with.", List.of(), Value.Types.INT),
                    new Expression(16, ExpressionCategories.EVENT, "Npc Talk About", "TalkAbout", "In a Npc Talk With About event, this is the character the Npc talks about.", List.of(), Value.Types.INT),
                    new Expression(17, ExpressionCategories.EVENT, "Period - Starting", "StartPeriod", "In a Period Ends Event, this is the new period starting.", List.of(), Value.Types.INT),
                    new Expression(18, ExpressionCategories.EVENT, "Period - Ending", "EndPeriod", "In a Period Ends Event, this is the old period that ended.", List.of(), Value.Types.INT),
                    new Expression(19, ExpressionCategories.CHARPROP, "Love Points", "%p.LOVE(towards: %p)", "The total sum of love points. This includes the love history,\n where each entry translates to 30 points, but which are limited to 30 history entrys (=900 points) across all 4 categories, as well as\nsingle points, that have not added up to 30 and were therefor not converted to love history yet.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(20, ExpressionCategories.CHARPROP, "Like Points", "%p.LIKE(towards: %p)", "The total sum of like points. This includes the like history, where each entry translates to 30 points, but which are limited to 30 history entrys (=900 points) across all 4 categories, as well as single points, that have not added up to 30 and were therefor not converted to like history yet.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(21, ExpressionCategories.CHARPROP, "Dislike Points", "%p.DISLIKE(towards: %p)", "The total sum of dislike points. This includes the dislike history, where each entry translates to 30 points, but which are limited to 30 history entrys (=900 points) across all 4 categories, as well as single points, that have not added up to 30 and were therefor not converted to dislike history yet.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(22, ExpressionCategories.CHARPROP, "Hate Points", "%p.HATE(towards: %p)","The total sum of hate points. This includes the hate history, where each entry translates to 30 points, but which are limited to 30 history entrys (=900 points) across all 4 categories, as well as single points, that have not added up to 30 and were therefor not converted to hate history yet.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(23, ExpressionCategories.MATH, "Float to Int", "Int(%p)", "Converts a Float to an Int by cutting off the decimals.", List.of(Value.Types.FLOAT ), Value.Types.INT),
                    new Expression(24, ExpressionCategories.CHARPROP, "Get Card Storage Int", "%p.GetInt(key: %p, default: %p)", "Gets the integer from the given cards storage entry. If the entry doesnt exist or holds a value of a different type, it returns the default value instead", List.of(Value.Types.INT, Value.Types.STRING, Value.Types.INT ), Value.Types.INT),
                    new Expression(25, ExpressionCategories.CHARPROP, "Virtue", "%p.Virtue", "The virtue of this character. 0 = Lowest ... 4 = Highest", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(26, ExpressionCategories.CHARPROP, "Personality", "%p.Personality", "The personalityId of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(27, ExpressionCategories.CHARPROP, "Voice Pitch", "%p.Pitch", "The voice pitch of this character. 0 = Lowest ... 4 = Highest", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(28, ExpressionCategories.CHARPROP, "Club", "%p.Club", "The clubId of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(29, ExpressionCategories.CHARPROP, "Club Value", "%p.ClubValue", "The club value of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(30, ExpressionCategories.CHARPROP, "Club Rank", "%p.ClubRank", "The club rank of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(31, ExpressionCategories.CHARPROP, "Intelligence", "%p.INT", "The intelligence of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(32, ExpressionCategories.CHARPROP, "Intelligence Value", "%p.INTValue", "The intelligence value of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(33, ExpressionCategories.CHARPROP, "Intelligence Rank", "%p.INTRank", "The intelligence rank of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(34, ExpressionCategories.CHARPROP, "Strength", "%p.STR", "The strength of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(35, ExpressionCategories.CHARPROP, "Strength Value", "%p.STRValue", "The strength value of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(36, ExpressionCategories.CHARPROP, "Strength Rank", "%p.STRRank", "The strength rank of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(37, ExpressionCategories.CHARPROP, "Sociability", "%p.Sociability", "The sociability of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(38, ExpressionCategories.CHARPROP, "Partner Count", "%p.PartnerCount", "Returns Partner Count of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(39, ExpressionCategories.EVENT, "Npc Original Response Chance", "OriginalResponsePercent", "If executed in a trigger with the Npc Answers Event, this is displayed success chance that the interaction had in percents.", List.of(), Value.Types.INT),
                    new Expression(40, ExpressionCategories.EVENT, "Npc Current Response Chance", "CurrentResponsePercent", "If executed in a trigger with the Npc Answers Event, this is the current interaction percent, modified by this or previously executed triggers of normal priority. Will not work for strong or absolute responses. using the Set Npc Response Percent Action", List.of(), Value.Types.INT),
                    new Expression(41, ExpressionCategories.CHARPROP, "Sex Orientation", "%p.Orientation", "The sexual orientation of this character. 0 = Straight, 1 = Lean Straight , 2 = Bisexual , 3 = Lean Homo, 4 = Homo", List.of(Value.Types.INT), Value.Types.INT),
                    new Expression(42, ExpressionCategories.STRINGS, "String Length", "Length(%p)", "Retrieves the length of the given string", List.of(Value.Types.STRING ), Value.Types.INT),
                    new Expression(43, ExpressionCategories.STRINGS, "First Index Of", "%p.FirstIndexOf(str: %p)", "Retrieves the first occurence of str string.", List.of(Value.Types.STRING, Value.Types.STRING ), Value.Types.INT),
                    new Expression(44, ExpressionCategories.MATH, "String to Int", "Int(%p)", "Converts a String to an Int. Returns 0 if string can't be converted.", List.of(Value.Types.STRING ), Value.Types.INT),
                    new Expression(45, ExpressionCategories.STRINGS, "First Index Of Starting At", "%p.FirstIndexOf(str: %p, from: %p)", "Retrieves the first occurence of str string starting from from: index.", List.of(Value.Types.STRING, Value.Types.STRING, Value.Types.INT ), Value.Types.INT),
                    new Expression(46, ExpressionCategories.CHARPROP, "Gender", "%p.Gender", "Character's gender. 0 means Male, 1 means Female. No tumblr here.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(47, ExpressionCategories.GENERAL, "Days Passed", "DaysPassed", "The current day in a game calendar (0 to 41).", List.of(), Value.Types.INT),
                    new Expression(48, ExpressionCategories.GENERAL, "Current Day", "CurrentDay", "0=Sunday, 1=Monday... 6=Saturday.", List.of(), Value.Types.INT),
                    new Expression(49, ExpressionCategories.GENERAL, "Current Period", "CurrentPeriod", "10 = sleep, 1 = day, 2 = nothing to talk, 3 = first lesson,4 = first break, 5 = sports, 6 = second break, 7 = club, 8 = end, 9 = home again", List.of(), Value.Types.INT),
                    new Expression(50, ExpressionCategories.CHARPROP, "Pregnancy Risk", "%p.PregnancyRisk(day: %p)", "Pregnancy risk of %p character at %p day. 2 = dangerous, 1 = safe, 0 = normal", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(51, ExpressionCategories.CHARPROP, "Current Style", "%p.Style", "Currently used Style.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(52, ExpressionCategories.CONVERSATION, "State", "ConversationState", "Current conversation state.", List.of(), Value.Types.INT),
                    new Expression(53, ExpressionCategories.CONVERSATION, "Actor", "Actor(%p) ", "Conversation actor's seat. Actors are listed from left to right starting at 0.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(54, ExpressionCategories.CONVERSATION, "NPC Response", "NPC Response", "Returns NPC response in a conversation. 0 is \"Yes\", 1 is \"No\", 2 is \"Huh?\", -1 is undefined.", List.of(), Value.Types.INT),
                    new Expression(55, ExpressionCategories.GENERAL, "Player Character", "PC", "Currently controlled character's seat.", List.of(), Value.Types.INT),
                    new Expression(56, ExpressionCategories.GENERAL, "Find Seat", "Seat(%p)", "Find a character with the provided full name(last name first name). Returns -1 if no such character is found", List.of(Value.Types.STRING ), Value.Types.INT),
                    new Expression(57, ExpressionCategories.CHARPROP, "Reject Count", "%p.Rejects", "Returns how many times this character was rejected.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(58, ExpressionCategories.CHARPROP, "Win Count", "%p.Wins", "Returns how many times this character won when competing over someone.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(59, ExpressionCategories.CHARPROP, "Victory Count", "%p.Victories", "Returns how many times this character won in a fight.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(60, ExpressionCategories.CHARPROP, "Skip Count", "%p.Skips", "Returns how many times this character skipped a class.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(61, ExpressionCategories.CONVERSATION, "PC Original Response", "PC Original Response", "Returns PC's original response in a conversation. 0 is \"Yes\", 1 is \"No\", 2 is \"Huh?\", -1 is undefined.", List.of(), Value.Types.INT),
                    new Expression(62, ExpressionCategories.CONVERSATION, "Conversation ID", "PC ConversationId", "Returns conversationID of the PC conversation.", List.of(), Value.Types.INT),
                    new Expression(63, ExpressionCategories.GENERAL, "Event ID", "Event", "Returns EventId.", List.of(), Value.Types.INT),
                    new Expression(64, ExpressionCategories.CHARPROP, "Find Style", "%p.Style(%p)", "Find Style index by name.", List.of(Value.Types.INT, Value.Types.STRING ), Value.Types.INT),
                    new Expression(65, ExpressionCategories.CHARPROP, "Get Mood Strength", "%p.Mood(%p)", "Get mood strength for the chosen mood.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(66, ExpressionCategories.CHARPROP, "Get Strongest Mood", "%p.Mood", "Get the most prevalent mood.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(67, ExpressionCategories.CHARPROP, "Get H compatibility", "%p.Compatibility(%p)", "Get compatibility with the selected character", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(68, ExpressionCategories.CHARPROP, "Get NPC Status", "%p.NpcStatus", "Get NPC status of the character. Returns -1=invalid, 0=still, 1=settle in location, 2=move to location, 3=walk to character, 4=follow, 7=talk, 8=minna", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(69, ExpressionCategories.CONVERSATION, "Answer Action", "AnswerId", "After the npc made a positive or negative answer, this id will be what he answers with.", List.of(), Value.Types.INT),
                    new Expression(70, ExpressionCategories.CONVERSATION, "Is Answering", "IsAnswering", "¯\\_(´*-*`)_/¯", List.of(), Value.Types.INT),
                    new Expression(71, ExpressionCategories.CHARPROP, "Cum Stat - Vaginal", "%p.VaginalCums(with: %p)", "Returns how many times this character got cummed inside their vagina by the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(72, ExpressionCategories.CHARPROP, "Cum Stat - Anal", "%p.AnalCums(with: %p)", "Returns how many times this character got cummed inside their rectum by the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(73, ExpressionCategories.CHARPROP, "Cum Stat - Oral", "%p.OralCums(with: %p)", "Returns how many times this character got cummed inside their mouth by the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(74, ExpressionCategories.CHARPROP, "Cum Stat - Total Vaginal", "%p.VaginalCumsTotal", "Returns how many times this character got cummed inside their vagina.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(75, ExpressionCategories.CHARPROP, "Cum Stat - Total Anal", "%p.AnalCumsTotal", "Returns how many times this character got cummed inside their rectum.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(76, ExpressionCategories.CHARPROP, "Cum Stat - Total Oral", "%p.OralCumsTotal", "Returns how many times this character got cummed inside their mouth.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(77, ExpressionCategories.CHARPROP, "Cum Stat - All", "%p.AllCums(with: %p)", "Returns how many times this character got cummed inside by the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(78, ExpressionCategories.CHARPROP, "Cum Stat - Total All", "%p.AllCumsTotal", "Returns how many times this character got cummed inside.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(79, ExpressionCategories.CHARPROP, "Climax Stat - Single", "%p.SingleClimax(with: %p)", "Returns how many times this character climaxed while having sex with the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(80, ExpressionCategories.CHARPROP, "Climax Stat - Total Single", "%p.SingleClimaxTotal", "Returns how many times this character climaxed while having sex.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(81, ExpressionCategories.CHARPROP, "Climax Stat - Simultaneous", "%p.SimClimax(with: %p)", "Returns how many times this character climaxed together with the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(82, ExpressionCategories.CHARPROP, "Climax Stat - Total Simultaneous", "%p.SimClimaxTotal", "Returns how many times this character climaxed together with everyone.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(83, ExpressionCategories.CHARPROP, "H Stat - Condoms Used", "%p.CondomsUsed(with: %p)", "Returns how many times this character used condoms with the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(84, ExpressionCategories.CHARPROP, "H Stat - Total Condoms Used", "%p.CondomsUsedTotal", "Returns how many times this character used condoms.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(85, ExpressionCategories.CHARPROP, "H Stat - Vaginal", "%p.VaginalH(with: %p)", "Returns how many times this character had vaginal sex with the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(86, ExpressionCategories.CHARPROP, "H Stat - Total Vaginal", "%p.VaginalHTotal", "Returns how many times this character had vaginal sex.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(87, ExpressionCategories.CHARPROP, "H Stat - Anal", "%p.AnalH(with: %p)", "Returns how many times this character had anal sex with the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(88, ExpressionCategories.CHARPROP, "H Stat - Total Anal", "%p.AnalHTotal", "Returns how many times this character had anal sex.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(89, ExpressionCategories.CHARPROP, "H Stat - All", "%p.AllH(with: %p)", "Returns how many times this character had sex with the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(90, ExpressionCategories.CHARPROP, "H Stat - Total All", "%p.AllHTotal", "Returns how many times this character had sex.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(91, ExpressionCategories.CHARPROP, "Cum Stat - Risky", "%p.RiskyCums(with: %p)", "Returns how many times this character got cummed inside on their risky days by the other character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(92, ExpressionCategories.CHARPROP, "Cum Stat - Total Risky", "%p.RiskyCumsTotal", "Returns how many times this character got cummed inside on their risky days.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(93, ExpressionCategories.CHARPROP, "Locked state", "%p.LockState", "Returns whether the character has the red circle around them or not. 1 is yes, 0 is no.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(94, ExpressionCategories.CHARPROP, "FapState", "%p.FapState", "Returns whether the character is fapping or not. 1 is yes, -1 is no.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(95, ExpressionCategories.CHARPROP, "Cherry Status", "%p.GetCherryState(with: %p)", "Returns whether other character attempted to take virginity of the card passed as the first argument. 1 - yes, 0 - no.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(96, ExpressionCategories.CONVERSATION, "Line", "ConversationLine", "Current conversation line.", List.of(), Value.Types.INT),
                    new Expression(97, ExpressionCategories.CHARPROP, "Current Room", "%p.CurrentRoom", "Current room the character is in.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(98, ExpressionCategories.CHARPROP, "Get PC TalkAbout", "PCTalkAbout", "Get the seat of the NPC that PC is talking about.", List.of(), Value.Types.INT),
                    new Expression(99, ExpressionCategories.EVENT, "INVALID", "INVALID", "This expression is invalid and is only a placeholder for the future.", List.of(), Value.Types.INT),
                    new Expression(100, ExpressionCategories.EVENT, "Npc Original Response", "OriginalResponse", "If executed in a trigger with the Npc Answers Event, this is the original Answer the NPC made", List.of(), Value.Types.BOOL),
                    new Expression(101, ExpressionCategories.EVENT, "Npc Current Response", "CurrentResponse", "If executed in a trigger with the Npc Answers Event, this is the current Answer, modified by this or previously executed Triggers. using the Set Npc Response Answer Action", List.of(), Value.Types.INT),
                    new Expression(102, ExpressionCategories.GENERAL, "Key pressed", "KeyPress", "Returns which key was pressed.", List.of(), Value.Types.INT),
                    new Expression(103, ExpressionCategories.GENERAL, "Get Target", "%p.GetTarget", "Returns the seat of the card that is the current target of the specified card.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(104, ExpressionCategories.CHARPROP, "Current Conversation", "%p.CurrConvo", "Get the conversation that some character is currently in.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(105, ExpressionCategories.EVENT, "H Position", "HPosition", "The index of the current H position of PC.", List.of(), Value.Types.INT),
                    new Expression(106, ExpressionCategories.CHARPROP, "Get Height", "%p.Height", "Get the height of the character. 0=short, 1=normal, 2=tall", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(107, ExpressionCategories.EVENT, "Active actor", "Active", "Get the active actor in h.", List.of(), Value.Types.INT),
                    new Expression(108, ExpressionCategories.EVENT, "Passive actor", "Passive", "Get the passive actor in h.", List.of(), Value.Types.INT),
                    new Expression(109, ExpressionCategories.CONVERSATION, "PC Effective Response", "PCEffectiveResponse", "Returns the answer that PC is going to act upon. Use only in After PC Response event. 0 is \"Yes\", 1 is \"No\", 2 is \"Huh?\", -1 is undefined.", List.of(), Value.Types.INT),
                    new Expression(110, ExpressionCategories.EVENT, "Npc Effective Response Chance", "EffectiveResponsePercent", "If executed in a trigger with the After NPC Answers Event, this is the current interaction percent, modified by any triggers of any priority. using the Set Npc Response Percent Action", List.of(), Value.Types.INT),
                    new Expression(111, ExpressionCategories.CHARPROP, "Get Figure", "%p.Figure", "Get the figure of the character. 0=thin, 1=normal, 2=chubby", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(112, ExpressionCategories.CHARPROP, "Get Opinion", "%p.Opinion(id: %p, towards: %p)", "Get the state of some opinion of the first character towards the second character.", List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(113, ExpressionCategories.CHARPROP, "Get Breast Size", "%p.Breast", "Get breast size of the character. 0=small, 1=normal, 2=large", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(114, ExpressionCategories.CHARPROP, "Get Decals", "%p.Decals(position: %p)", "Get strength of decals at certain body part of some character. Use only during h! For position 0 - chest, 1 - back, 2 - crotch / legs, 3 - butt, 4 - face. Decals have multiple possible strengths (0-3), 0 being no decals and 3 being strongest.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(115, ExpressionCategories.CHARPROP, "Action About Room", "%p.ActionAboutRoom", "Returns the ID of the room that the character is talking about.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(116, ExpressionCategories.CHARPROP, "Fighting Stance", "%p.FightStance", "The fighting stance of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(117, ExpressionCategories.EVENT, "Dominant actor", "Dominant", "Get the dominant actor in h.", List.of(), Value.Types.INT),
                    new Expression(118, ExpressionCategories.EVENT, "Submissive actor", "Submissive", "Get the submissive actor in h.", List.of(), Value.Types.INT),
                    new Expression(119, ExpressionCategories.CHARPROP, "Get Stamina", "%p.GetStamina", "Gets the current amount of stamina.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(120, ExpressionCategories.EVENT, "Get Period Timer", "PeriodTimer", "Returns the number of seconds that have passed in the current period.", List.of(), Value.Types.INT),
                    new Expression(121, ExpressionCategories.GENERAL, "Get Class Storage Int", "GetInt(key: %p, default: %p)", "Gets the integer from the given class storage entry. If the entry doesnt exist or holds a value of a different type, it returns the default value instead", List.of(Value.Types.STRING, Value.Types.INT ), Value.Types.INT),
                    new Expression(122, ExpressionCategories.GENERAL, "Get PC Target", "PCTarget", "Returns the seat of the card that is the current target of the player character.", List.of(), Value.Types.INT),
                    new Expression(123, ExpressionCategories.CHARPROP, "Virtue Modifier", "%p.VirtueMod(%p)", "The value of the specified virtue modifier.", List.of(Value.Types.INT, Value.Types.STRING ), Value.Types.INT),
                    new Expression(124, ExpressionCategories.CHARPROP, "Real Virtue", "%p.RealVirtue", "The real unlocked virtue of the character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(125, ExpressionCategories.CHARPROP, "Sociability Modifier", "%p.SociabilityMod(%p)", "The value of the specified sociability modifier.", List.of(Value.Types.INT, Value.Types.STRING ), Value.Types.INT),
                    new Expression(126, ExpressionCategories.CHARPROP, "Real Sociability", "%p.RealSociability", "The real unlocked socibility of the character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(127, ExpressionCategories.CHARPROP, "Intelligence Modifier", "%p.INTMod(%p)", "The value of the specified intelligence value modifier.", List.of(Value.Types.INT, Value.Types.STRING ), Value.Types.INT),
                    new Expression(128, ExpressionCategories.CHARPROP, "Real Intelligence Value", "%p.RealINTValue", "The real unlocked intelligenve value of the character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(129, ExpressionCategories.CHARPROP, "Strength Modifier", "%p.STRMod(%p)", "The value of the specified strength value modifier.", List.of(Value.Types.INT, Value.Types.STRING ), Value.Types.INT),
                    new Expression(130, ExpressionCategories.CHARPROP, "Real Strength Value", "%p.RealSTRValue", "The real unlocked strength value of the character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(131, ExpressionCategories.CHARPROP, "Club Modifier", "%p.ClubMod(%p)", "The value of the specified club value modifier.", List.of(Value.Types.INT, Value.Types.STRING ), Value.Types.INT),
                    new Expression(132, ExpressionCategories.CHARPROP, "Real Club Value", "%p.RealClubValue", "The real unlocked club value of the character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(133, ExpressionCategories.CHARPROP, "Get LocationTarget", "%p.LocationTarget", "Returns the m_locationTarget of the character. Believed to be room that PC is targeting when he clicks around, but unavailable from NPC answers event.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(134, ExpressionCategories.EVENT, "Npc Strong Response Modifier", "StrongResponseModifier", "Use only in After NPC Response event. This expression will ONLY return the strong response modifier that any triggers set. It will be -1 if no triggers had done so.", List.of(), Value.Types.INT),
                    new Expression(135, ExpressionCategories.EVENT, "Npc Absolute Response Modifier", "AbsoluteResponseModifier", "Use only in After NPC Response event. This expression will ONLY return the absolute response modifier that any triggers set. It will be -1 if no triggers had done so.", List.of(), Value.Types.INT),
                    new Expression(136, ExpressionCategories.EVENT, "Get PC Room Target", "PCRoomTarget", "Use only in NPC/After NPC answer event. This expression will return the room that PC is walking to.", List.of(), Value.Types.INT),
                    new Expression(137, ExpressionCategories.CHARPROP, "Trait Modifier", "%p.TraitMod(trait: %p, modName: %p)", "The value of the specified trait value modifier.", List.of(Value.Types.INT, Value.Types.INT, Value.Types.STRING ), Value.Types.INT),
                    new Expression(138, ExpressionCategories.CHARPROP, "Unbount Trait Value", "%p.UnboundTraitValue(trait: %p)", "The unbound trait value of the character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(139, ExpressionCategories.CHARPROP, "Get Current Outfit", "%p.CurrOutfit", "Returns the ID (0-3) of the current outfit of the character. Use to figure out whether they are in uniform, sports, swim or club outfit.", List.of(Value.Types.INT), Value.Types.INT),
                    new Expression(140, ExpressionCategories.CHARPROP, "Get lover days", "%p.GetLoverDays(with: %p)", "Returns the amount of days that this card has been a lover with the other for.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.INT),
                    new Expression(141, ExpressionCategories.CHARPROP, "Get Sports Exam Grade", "%p.SportsExamGrade", "Returns the grade this card got on sports exam, from 0 to 5, where 0 is F and 5 is A", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(142, ExpressionCategories.CHARPROP, "Get Academic Exam Grade", "%p.AcademExamGrade", "Returns the grade this card got on academic exam, from 0 to 5, where 0 is F and 5 is A", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(143, ExpressionCategories.CHARPROP, "Get Club Exam Grade", "%p.ClubExamGrade", "Returns the grade this card got on club tournament, from 0 to 5, where 0 is Eliminated 1st round and 5 is Champion", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(144, ExpressionCategories.CHARPROP, "Sexual Partner Count", "%p.HPartnerCount", "Returns Sexual Partner Count of this character.", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(145, ExpressionCategories.GENERAL, "Club Type", "ClubType(%p)", "Returns the type of a provided club id", List.of(Value.Types.INT ), Value.Types.INT),
                    new Expression(146, ExpressionCategories.EVENT, "Get Love", "GetLove", "Returns the amount of love points that are about to be applied. Only works in relationship points changed event.", List.of(), Value.Types.INT),
                    new Expression(147, ExpressionCategories.EVENT, "Get Like", "GetLike", "Returns the amount of like points that are about to be applied. Only works in relationship points changed event.", List.of(), Value.Types.INT),
                    new Expression(148, ExpressionCategories.EVENT, "Get Dislike", "GetDislike", "Returns the amount of dislike points that are about to be applied. Only works in relationship points changed event.", List.of(), Value.Types.INT),
                    new Expression(149, ExpressionCategories.EVENT, "Get Hate", "GetHate", "Returns the amount of hate points that are about to be applied. Only works in relationship points changed event.", List.of(), Value.Types.INT),
                    new Expression(150, ExpressionCategories.EVENT, "Relationship Points Changed Towards", "RelationshipTowards", "Returns the character that TriggerCard will have relationship points applied towards within Relationship Points Changed event.", List.of(), Value.Types.INT),
                    new Expression(151, ExpressionCategories.EVENT, "Get Delayed Event's Period", "DelayedEventPeriod", "In Delayed Execution Event returns the period it was emitted from.", List.of(), Value.Types.INT),
                    new Expression(152, Action.ActionCategories.GENERAL, "Call LUA Function", "LUA(%p)", "Call supplemental lua int function.", List.of(Value.Types.STRING), Value.Types.INT),
                    new Expression(153, ExpressionCategories.CHARPROP, "Get Skirt State", "%p.SkirtState", "Returns skirt state of some card. 0 - long, 1 - short, 2 - rolled up, 3 - no skirt", List.of(Value.Types.INT ), Value.Types.INT)
            },
            { //BOOL
                    new Expression(ExpressionTypes.CONSTANT.id, ExpressionCategories.GENERAL, "Constant", "Constant", "An arbitrary constant to input", List.of(), Value.Types.BOOL),
                    new Expression(ExpressionTypes.VAR.id, ExpressionCategories.GENERAL, "Variable", "Variable", "A Variable", List.of(), Value.Types.BOOL),
                    new Expression(ExpressionTypes.NAMEDCONSTANT.id, ExpressionCategories.GENERAL, "Enumeration", "Enumeration", "A known constant with a name", List.of(), Value.Types.BOOL),
                    new Expression(4,ExpressionCategories.COMPARISION_BOOL, "Logical And","(%p && %p)","Logical and, including short-circut evaluation", List.of(Value.Types.BOOL, Value.Types.BOOL ),Value.Types.BOOL),
                    new Expression(5, ExpressionCategories.COMPARISION_BOOL, "Logical Or","(%p || %p)", "Logical or, including short-circut evaluation", List.of(Value.Types.BOOL, Value.Types.BOOL ), Value.Types.BOOL),
                    new Expression(6, ExpressionCategories.COMPARISION_INT, "Greater Than", "(%p > %p)", "Greater-Than", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(ExpressionTypes.BOOL_GTE_INT.id, ExpressionCategories.COMPARISION_INT, "Greater Than or Equal", "(%p >= %p)", "Greater-Than or equal", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(8, ExpressionCategories.COMPARISION_INT, "Equal", "(%p == %p)", "Equal", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(9, ExpressionCategories.COMPARISION_INT, "Less Than or Equal", "(%p <= %p)", "less than or equal", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(10, ExpressionCategories.COMPARISION_INT, "Less Than", "(%p < %p)", "less than", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(ExpressionTypes.BOOL_NOT.id, ExpressionCategories.COMPARISION_BOOL, "Not", "!(%p)", "Logical Not", List.of(Value.Types.BOOL ), Value.Types.BOOL),
                    new Expression(12, ExpressionCategories.COMPARISION_STRING, "String - Equal", "(%p == %p)", "Compares two strings", List.of(Value.Types.STRING, Value.Types.STRING ), Value.Types.BOOL),
                    new Expression(13, ExpressionCategories.COMPARISION_INT, "Not Equal", "(%p != %p)", "Not Equal", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(14, ExpressionCategories.EVENT, "Npc Original Response Success", "OriginalResponseSuccess", "If executed in a trigger with the Npc Answers Event, this is the original Answer the NPC made", List.of(), Value.Types.BOOL),
                    new Expression(15, ExpressionCategories.EVENT, "Npc Normal Response Success", "NormalResponseSuccess", "If executed in a trigger with the Npc Answers Event, this is the normal answer, modified by this or previously executed Triggers using the Set Npc Response Answer Success Action. Will not work for strong or absolute responses. Within After NPC Response event, this will return the original answer the NPC gave, or the answer modified by normal response modifier.", List.of(), Value.Types.BOOL),
                    new Expression(16, ExpressionCategories.CHARPROP, "Is Seat Filled", "%p.IsSeatFilled", "Whether the given Seat has a character behind it. Characters are identified using their seat number. Use this in a loop through all seatsdo determine if a character exists or not.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(17, ExpressionCategories.CHARPROP, "Is Lover", "%p.HasLover(%p)", "True if the characters are currently in a relationship. (technically, it checks if the first parameter is in a relationship with the second)", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(18, ExpressionCategories.COMPARISION_FLOAT, "Greater Than","(%p > %p)","Greater-Than", List.of(Value.Types.FLOAT, Value.Types.FLOAT ),Value.Types.BOOL),
                    new Expression(19, ExpressionCategories.COMPARISION_FLOAT, "Greater Than or Equal", "(%p >= %p)", "Greater-Than or equal", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.BOOL),
                    new Expression(20, ExpressionCategories.COMPARISION_FLOAT, "Equal", "(%p == %p)", "Equal", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.BOOL),
                    new Expression(21, ExpressionCategories.COMPARISION_FLOAT, "Less Than or Equal", "(%p <= %p)", "less than or equal", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.BOOL),
                    new Expression(22, ExpressionCategories.COMPARISION_FLOAT, "Less Than", "(%p < %p)", "less than", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.BOOL),
                    new Expression(23, ExpressionCategories.COMPARISION_FLOAT, "Not Equal", "(%p != %p)", "not equal", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.BOOL),
                    new Expression(24, ExpressionCategories.CHARPROP, "Get Card Storage Bool", "%p.GetBool(key: %p, default: %p)", "Gets the bool from the given cards storage entry. If the entry doesnt exist or holds a value of a different type, it returns the default value instead", List.of(Value.Types.INT, Value.Types.STRING, Value.Types.BOOL ), Value.Types.BOOL),
                    new Expression(25, ExpressionCategories.CHARPROP, "Trait", "%p.Trait(%p)", "", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(26, ExpressionCategories.GENERAL, "Check Interruption Action", "%p.isInterrupt", "Returs true if INTERRUPT_COMPETE, INTERRUPT_STOP_QUARREL, INTERRUPT_WHAT_ARE_YOU_DOING, H_END, H_NOTE, BREAK_CHAT, BREAK_H", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(27, ExpressionCategories.GENERAL, "Check Minna Action", "%p.isMinna", "Returs true if MINNA_BE_FRIENDLY, MINNA_CLUB, MINNA_COME, MINNA_EAT, MINNA_H, MINNA_KARAOKE, MINNA_LUNCH, MINNA_REST, MINNA_SPORTS, MINNA_STUDY", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(28, ExpressionCategories.GENERAL, "Check Force Action", "%p.isForce", "Returns true if FIGHT, FORCE_H, FORCE_IGNORE, FORCE_PUT_THIS_ON, FORCE_SHOW_THAT, INSULT, SLAP", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(29, ExpressionCategories.GENERAL, "Check Sex Action", "%p.isSex", "Returns true if FOLLOW_ME_H, FORCE_H, MINNA_H, NORMAL_H, NO_PROMPT_H, SKIP_CLASS_H, SKIP_CLASS_SURPRISE_H, STUDY_HOME_H, LEWD_REWARD", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(30, ExpressionCategories.GENERAL, "Check NoPrompt Action", "%p.isNoPrompt", "Returns true if EXPLOITABLE_LINE, FORCE_BREAKUP, GOOD_BYE_KISS, GOOD_MORNING_KISS, I_SAW_SOMEONE_HAVE_H, I_WILL_CHEAT, MURDER, MURDER_NOTICE, NEVERMIND, NO_PROMPT_H, NO_PROMPT_KISS, REVEAL_PREGNANCY, SHAMELESS, SLAP, SOMEONE_GOT_CONFESSED_TO, SOMEONE_LIKES_YOU, STOP_FOLLOWING, TOGETHER_FOREVER", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(31, ExpressionCategories.GENERAL, "Check Game Over Action", "%p.isGameOver", "Returns true if MURDER, REVEAL_PREGNANCY, TOGETHER_FOREVER", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(32, ExpressionCategories.GENERAL, "Check No Target Action", "%p.isNoTarget", "Returns true if CHANGE_CLOTHES, DO_CLUB, DO_EXERCISE, DO_STUDY", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(33, ExpressionCategories.MATH, "Roll Float", "Roll(%p)", "Generates a random [0.0, 1.0] float value and if it's less than or equal to the provided argument returns true. Arguments over 1.0 always roll success", List.of(Value.Types.FLOAT ), Value.Types.BOOL),
                    new Expression(34, ExpressionCategories.MATH, "Roll Int", "Roll(%p)", "Generates a random [1, 100] integer value and if it's less than or equal to the provided argument returns true. Arguments over 100 always roll success", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(35, ExpressionCategories.CHARPROP, "Sex Experience: Vaginal", "%p.SexXP", "Returns true if the character is not a virgin.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(36, ExpressionCategories.CHARPROP, "Sex Experience: Anal", "%p.AnalXP", "Returns true if the character is not an anal virgin.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(37, ExpressionCategories.CHARPROP, "Has Lovers", "%p.HasLovers", "Returns true if the character is in at least one rleationship.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(38, ExpressionCategories.EVENT, "Get Auto-PC", "AutoPC", "Returns whether AutoPC is toggled on or off.", List.of(), Value.Types.BOOL),
                    new Expression(39, ExpressionCategories.CHARPROP, "Get Cum", "%p.GetCum", "Returns true if the character has cum in their mouth.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(40, ExpressionCategories.CHARPROP, "Get Tears", "%p.GetTears", "Returns true if the character is crying.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(41, ExpressionCategories.CHARPROP, "Get Highlight", "%p.GetHighlight", "Returns true if the character has highlight in their eyes.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(42, ExpressionCategories.CHARPROP, "Get Glasses", "%p.GetGlasses", "Returns true if the character has their glasses on.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(43, ExpressionCategories.GENERAL, "Pose exists", "PoseExists(%p)", "Return whether a .pose exists.", List.of(Value.Types.STRING ), Value.Types.BOOL),
                    new Expression(44, ExpressionCategories.EVENT, "Npc Effective Response Success", "EffectiveResponseSuccess", "If executed in a trigger with the After NPC Answers Event, this is the current answer that the NPC will act upon, modified by any previously executed Triggers.", List.of(), Value.Types.BOOL),
                    new Expression(45, ExpressionCategories.CHARPROP, "Get H Preference", "%p.HPreference(%p)", "Returns whether the card has some H preference.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(46, ExpressionCategories.GENERAL, "Get Class Storage Bool", "GetBool(key: %p, default: %p)", "Gets the bool from the given class storage entry. If the entry doesnt exist or holds a value of a different type, it returns the default value instead", List.of(Value.Types.STRING, Value.Types.BOOL ), Value.Types.BOOL),
                    new Expression(47, ExpressionCategories.GENERAL, "Check Skip Action", "%p.isSkip", "Returns true if SKIP_CLASS, SKIP_CLASS_H, SKIP_CLASS_SURPRISE_H, GO_HOME_TOGETHER, GO_KARAOKE_TOGETHER, GO_PLAY_TOGETHER, GO_EAT_TOGETHER", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(48, ExpressionCategories.GENERAL, "Check Private Room", "%p.isPrivateRoom", "Returns true if the location ID is one of the private rooms.", List.of(Value.Types.INT ), Value.Types.BOOL),
                    new Expression(49, ExpressionCategories.GENERAL, "Has Date With", "%p.HasDateWith(%p)", "Returns true if the characters arranged to have a date on Sunday.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(50, ExpressionCategories.GENERAL, "Promised a Lewd Reward To", "%p.LewdPromise(%p)", "Returns true if the first character promised a lewd reward to the second character.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.BOOL),
                    new Expression(51, ExpressionCategories.EVENT, "Get Delayed Event Required", "DelayedEventRequired", "In Delayed Execution Event returns whether this is a required event.", List.of(), Value.Types.BOOL),
                    new Expression(52, Action.ActionCategories.GENERAL, "Call LUA Function", "LUA(%p)", "Call supplemental lua bool function.", List.of(Value.Types.STRING), Value.Types.BOOL),
                    new Expression(53, ExpressionCategories.EVENT, "Get Condom-Override", "CondomOverride", "Returns whether CondomOverride is toggled on or off.", List.of(), Value.Types.BOOL),
                    new Expression(54, ExpressionCategories.EVENT, "Get Condom-Value", "CondomValue", "Returns whether the global condom setting is set to have them on or off.", List.of(), Value.Types.BOOL),
            },
            { //FLOAT
                    new Expression(ExpressionTypes.CONSTANT.id, ExpressionCategories.GENERAL, "Constant", "Constant", "An arbitrary constant to input", List.of(), Value.Types.FLOAT),
                    new Expression(ExpressionTypes.VAR.id, ExpressionCategories.GENERAL, "Variable", "Variable", "A Variable", List.of(), Value.Types.FLOAT),
                    new Expression(ExpressionTypes.NAMEDCONSTANT.id, ExpressionCategories.GENERAL, "Enumeration", "Enumeration", "A known constant with a name", List.of(), Value.Types.FLOAT),
                    new Expression(4, ExpressionCategories.MATH, "Random Float", "RandomFloat(min: %p, max: %p)", "Generates a random float between the two arguments (including both)", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.FLOAT),
                    new Expression(ExpressionTypes.INT_PLUS.id, ExpressionCategories.MATH, "+", "(%p + %p)", "Adds two floats", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.FLOAT),
                    new Expression(6, ExpressionCategories.MATH, "-", "(%p - %p)", "Substracts two floats", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.FLOAT),
                    new Expression(7, ExpressionCategories.MATH, "/", "(%p / %p)", "Divide two floats", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.FLOAT),
                    new Expression(8, ExpressionCategories.MATH, "*", "(%p * %p)", "Multiply two floats", List.of(Value.Types.FLOAT, Value.Types.FLOAT ), Value.Types.FLOAT),
                    new Expression(9, ExpressionCategories.MATH, "Int to Float", "Float(%p)", "Converts an Int to Float", List.of(Value.Types.INT ), Value.Types.FLOAT),
                    new Expression(10,ExpressionCategories.CHARPROP, "Get Card Storage Float","%p.GetFloat(key: %p, default: %p)", "Gets the float from the given cards storage entry. If the entry doesnt exist or holds a value of a different type, it returns the default value instead", List.of(Value.Types.INT, Value.Types.STRING, Value.Types.FLOAT ),Value.Types.FLOAT),
                    new Expression(11, ExpressionCategories.MATH, "String to Float", "Float(%p)", "Converts a String to a Float.", List.of(Value.Types.STRING ), Value.Types.FLOAT),
                    new Expression(12, ExpressionCategories.CHARPROP, "Sex Orientation Multiplier", "%p.OrientationMultiplier(towards: %p)", "The multiplier used when calculating interaction chances depending on actors' sex orientations and genders. Returns either 1.0, 0.5 or 0.0", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.FLOAT),
                    new Expression(13,ExpressionCategories.GENERAL, "Get Class Storage Float","GetFloat(key: %p, default: %p)", "Gets the float from the given class storage entry. If the entry doesnt exist or holds a value of a different type, it returns the default value instead", List.of(Value.Types.STRING, Value.Types.FLOAT ),Value.Types.FLOAT),
                    new Expression(14, ExpressionCategories.CHARPROP, "Like Orientation Multiplier", "%p.LikeOrientationMultiplier(towards: %p)", "Returns 0 when characters can't get like points towards each other, 0.5 when their like gain is halved, and 1 when they get full amount of like points from interactions.", List.of(Value.Types.INT, Value.Types.INT ), Value.Types.FLOAT),
                    new Expression(15, Action.ActionCategories.GENERAL, "Call LUA Function", "LUA(%p)", "Call supplemental lua float function.", List.of(Value.Types.STRING), Value.Types.FLOAT)
            },
            { //STRING
                    new Expression(ExpressionTypes.CONSTANT.id, ExpressionCategories.GENERAL, "Constant", "Constant", "An arbitrary constant to input", List.of(), Value.Types.STRING),
                    new Expression(ExpressionTypes.VAR.id, ExpressionCategories.GENERAL, "Variable", "Variable", "A Variable", List.of(), Value.Types.STRING),
                    new Expression(ExpressionTypes.NAMEDCONSTANT.id, ExpressionCategories.GENERAL, "Enumeration", "Enumeration", "A known constant with a name", List.of(), Value.Types.STRING),
                    new Expression(4, ExpressionCategories.STRINGS, "Substring", "%p.Substring(startIdx: %p, length: %p)", "A substring that starts at the first parameter (inclusive) and has a specific length", List.of(Value.Types.STRING,Value.Types.INT, Value.Types.INT), Value.Types.STRING),
                    new Expression(5, ExpressionCategories.CHARPROP, "Last Name", "%p.LastName", "Last name of this character. Family name. The top name in the maker.", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(6, ExpressionCategories.CHARPROP, "First Name", "%p.FirstName", "First name of this character. Given name. The bottom name in the maker.", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(7, ExpressionCategories.CHARPROP, "Get Card Storage String", "%p.GetStr(key: %p, default: %p)", "Gets the string from the given cards storage entry. If the entry doesnt exist or holds a value of a different type, it returns the default value instead", List.of(Value.Types.INT, Value.Types.STRING, Value.Types.STRING ), Value.Types.STRING),
                    new Expression(8, ExpressionCategories.CHARPROP, "+", "(%p + %p)", "Concatenate two strings", List.of(Value.Types.STRING, Value.Types.STRING ), Value.Types.STRING),
                    new Expression(9, ExpressionCategories.MATH, "Int to String", "String(%p)", "Converts an Int to String", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(10, ExpressionCategories.MATH, "Float to String", "String(%p)", "Converts a Float to String", List.of(Value.Types.FLOAT ), Value.Types.STRING),
                    new Expression(11, ExpressionCategories.MATH, "Bool to String", "String(%p)", "Converts a Bool to String", List.of(Value.Types.BOOL ), Value.Types.STRING),
                    new Expression(12, ExpressionCategories.CHARPROP, "Description", "%p.Description", "This character's description", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(13, ExpressionCategories.STRINGS, "Replace substring", "%p.Replace(from: %p, to: %p, str: %p)", "Replace substring starting from the from: and ending with to:", List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT, Value.Types.STRING ), Value.Types.STRING),
                    new Expression(14, ExpressionCategories.CHARPROP, "Last Sex Partner", "%p.LastSex", "Returns the full name of the last sex partner as it appears on the character sheet.", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(15, ExpressionCategories.CHARPROP, "First Sex Partner", "%p.FirstSex", "Returns the full name of the first sex partner as it appears on the character sheet.", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(16, ExpressionCategories.CHARPROP, "First Anal Partner", "%p.FirstAnal", "Returns the full name of the first anal partner as it appears on the character sheet.", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(17, ExpressionCategories.CHARPROP, "Item - Lover's", "%p.LoverItem", "Returns the Lover's item", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(18, ExpressionCategories.CHARPROP, "Item - Friend's", "%p.FriendItem", "Returns the Friend's item", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(19, ExpressionCategories.CHARPROP, "Item - Sexual", "%p.SexualItem", "Returns the Sexual item", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(20, ExpressionCategories.CHARPROP, "Full Name", "%p.FullName", "Full name of the character in \"LastName FirstName\" format.", List.of(Value.Types.INT ), Value.Types.STRING),
                    new Expression(21, ExpressionCategories.GENERAL, "LUA Add Parameter", "%p << %p", "Add parameter to the LUA Procedure string.", List.of(Value.Types.STRING, Value.Types.STRING ), Value.Types.STRING),
                    new Expression(22, ExpressionCategories.GENERAL, "Get Class Storage String", "GetStr(key: %p, default: %p)", "Gets the string from the given class storage entry. If the entry doesnt exist or holds a value of a different type, \n  it returns the default value instead", List.of(Value.Types.STRING, Value.Types.STRING ), Value.Types.STRING),
                    new Expression(23, ExpressionCategories.EVENT,"Get Delayed Event Label", "DelayedEventLabel","Gets the name of the delayed event. Returns empty string if it's an invalid event.", List.of(), Value.Types.STRING), new Expression(24, ExpressionCategories.STRINGS, "Get comma separated value by index", "%p.GetCSV(idx: %p, separator: %p)", "Retrieves a substring of a comma separated string. E.g. \"one,two,three\".GetCSV(idx: 1, separator: \",\") would return  \"two\" .", List.of(Value.Types.STRING,Value.Types.INT, Value.Types.STRING), Value.Types.STRING),
                    new Expression(25, Action.ActionCategories.GENERAL, "Call LUA Function", "LUA(%p)", "Call supplemental lua string function.", List.of(Value.Types.STRING), Value.Types.STRING)
            }
     };



    public static Expression fromID(Value.Types type, int id){
        if(id < 1 || id > g_Expressions[type.ordinal()].length) return null;
        return g_Expressions[type.ordinal()][id-1];

    }
    enum ExpressionCategories implements Category{
        GENERAL("General"),
        EVENT("Event Response"),
        MATH("Math"),
        CHARPROP("Character Property"),
        COMPARISION_INT("Comparison - Int"),
        COMPARISION_STRING("Comparison - String"),
        COMPARISION_BOOL("Comparison - Bool"),
        COMPARISION_FLOAT("Comparison - Float"),
        STYLESEDIT("AAU Styles"),
        CONVERSATION("Conversation"),
        STRINGS("Strings");

        private final String name;

        ExpressionCategories(String name){
            this.name = name;
        }
    }
    public enum ExpressionTypes{
        INVALID(0),
        CONSTANT(1),
        VAR(2),
        NAMEDCONSTANT(3),
        INT_PLUS(5),
        BOOL_GTE_INT(7),
        BOOL_NOT(11);

        public final int id;
        ExpressionTypes(int id){
            this.id = id;
        }
    }

    public static final class ParameterisedExpression{
        private final Expression expression;
        private final List<ParameterisedExpression> actualParameters;
        private final Value constant;
        private final wString varName;
        private final NamedConstant namedConstant;

        private ParameterisedExpression(Expression expression, List<ParameterisedExpression> actualParameters, Value constant, wString varName, NamedConstant namedConstant){
            this.expression = expression;
            this.actualParameters = actualParameters;
            this.constant = constant;
            this.varName = varName;
            this.namedConstant = namedConstant;
        }

        public ParameterisedExpression(Value.Types type, /*DWORD*/ int exprId, List<ParameterisedExpression> actualParams){
            this(Expression.fromID(type, exprId),actualParams,null,null,null);
        }
        public ParameterisedExpression(Value.Types type, Value constant){
            this(Expression.fromID(type, ExpressionTypes.CONSTANT.id),null,constant,null,null);
        }
        public ParameterisedExpression(Value.Types type, wString var){
            this(Expression.fromID(type,ExpressionTypes.VAR.id),null,null,var,null);
        }
        public ParameterisedExpression(Expression exp, int namedConstandID){
            this(exp,null,null,null,NamedConstant.FromId(exp.returnType,namedConstandID));
        }

    }

}
