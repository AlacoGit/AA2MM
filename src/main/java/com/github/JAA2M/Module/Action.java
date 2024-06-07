package com.github.JAA2M.Module;

import java.util.List;

public record Action(/*DWORD*/ int id, ActionCategories categories, String name, String interactiveName, String description, List<Value.Types> parameters) {
        public record ParameterisedAction(Action action, List<Expression.ParameterisedExpression> actualParameters){
                ParameterisedAction(/*DWORD*/ int ActionID, List<Expression.ParameterisedExpression> actualParameters){
                        this(Action.fromId(ActionID),actualParameters);
                }

                public String formatPrintable(){
                    String ret = action.interactiveName.replace("%p","%s");
                    return ret.formatted(actualParameters.toArray());
                    //return action.interactiveName.replace("%p","%s").formatted(actualParameters);
                }
        }
        public static final Action[] g_Actions = new Action[]{
            new Action(
                    Actions.SETVAR.id, ActionCategories.GENERAL, "Set Variable", "%p = %p", "Sets the value of a given variable to a new one",
                    List.of(Value.Types.INVALID, Value.Types.INVALID)
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    Actions.IF.id, ActionCategories.FLOW_CONTROL, "If", "If (%p) then",
                    "Executes Actions if Boolean Expression is true. Use with \"Else if\" and \"Else\" actions.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    Actions.ELSEIF.id, ActionCategories.FLOW_CONTROL, "Else If", "Else If (%p) then",
                    "Executes Actions if the previous If Action was not executed, and the Boolean Expression is true. Use with \"If\" and \"Else\" actions. If this action does not preceed a \"If\" action, it acts as an \"If\" action instead.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    Actions.ELSE.id, ActionCategories.FLOW_CONTROL, "Else", "Else",
                    "Executes Actions if the preceeding \"If\" and \"Else If\" actions were all not Executed. Use with \"If\" and \"Else\" actions. If this action does not preceed a \"If\" or \"Else if\" action, it is always executed.",
                    List.of()
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    Actions.CONDJUMP.id,ActionCategories.FLOW_CONTROL,"Conditional Jump","Jump %p Actions if (%p)",
                    "Skips additional actions if the given condition is true. May skip negative amounts to go back.",
                    List.of(Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::ConditionalJump*/
            ),
            new Action(
                    Actions.LOOP.id, ActionCategories.FLOW_CONTROL, "Loop", "Loop",
                    "Loops the subactions. Can be exited using break or repeated by using continue actions",
                    List.of()
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    Actions.CONDBREAK.id, ActionCategories.FLOW_CONTROL, "Break If", "Break If (%p)",
                    "Breaks out of the enclosing Loop action if the given condition is true. No effect if no loop is around.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    Actions.CONDCONTINUE.id, ActionCategories.FLOW_CONTROL, "Continue If", "Continue If (%p)",
                    "Goes back to the loop header of the enclosing Loop action if the given condition is true. No effect if no loop is around.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    Actions.FORLOOP.id, ActionCategories.FLOW_CONTROL, "For Loop", "For %p from %p till %p (exclusive)",
                    "Sets the given integer variable to the start value, then loops the subactions, increasing the variable by one after each pass, until the integer is greater or equal to the target value. Break and Continue may be used.",
                    List.of(Value.Types.INVALID, Value.Types.INT, Value.Types.INT)
                    /*&Thread::ShouldNotBeImplemented*/
            ),
            new Action(
                    10, ActionCategories.MODIFY_CARD, "Switch Style", "%p.Style = %p", "Switches current character style.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SwitchCardStyle*/
            ),

            new Action(
                    11, ActionCategories.FLOW_CONTROL, "End Execution", "End", "Ends execution of this thread. Similar to a return statement.",
                    List.of()
                    /*&Thread::EndExecution*/
            ),
            new Action(
                    12, ActionCategories.MODIFY_CHARACTER, "Add Love Points", "%p.AddLOVE(towards: %p, amount: %p)",
                    "Adds a certain amount of love points. 30 love points become one love interaction. A character can have up to 30 interactions in total; after that, earlier interactions will be replaced.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT )
                    /*&Thread::AddCardLovePoints*/
            ),
            new Action(
                    13, ActionCategories.MODIFY_CHARACTER, "Add Like Points", "%p.AddLIKE( towards: %p, points: %p)",
                    "Adds a certain amount of like points. 30 like points become one like interaction. A character can have up to 30 interactions in total; after that, earlier interactions will be replaced.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT )
                    /*&Thread::AddCardLikePoints*/
            ),
            new Action(
                    14, ActionCategories.MODIFY_CHARACTER, "Add Dislike Points", "%p.AddDISLIKE( towards: %p, points: %p)",
                    "Adds a certain amount of dislike points. 30 dislike points become one dislike interaction. A character can have up to 30 interactions in total; after that, earlier interactions will be replaced.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::AddCardDislikePoints*/
            ),
            new Action(
                    15, ActionCategories.MODIFY_CHARACTER, "Add Hate Points", "%p.AddHATE( towards: %p, points: %p)",
                    "Adds a certain amount of hate points. 30 hate points become one hate interaction. A character can have up to 30 interactions in total; after that, earlier interactions will be replaced.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::AddCardHatePoints*/
            ),
            new Action(
                    16, ActionCategories.MODIFY_CHARACTER, "Add Points", "%p.AddPoints( towards: %p, %p points: %p)",
                    "Adds a certain amount of points. Point type is between 0 or 3, or use one of the named constants. 30 hate points become one hate interaction. A character can have up to 30 interactions in total; after that, earlier interactions will be replaced. Example: { TriggerCard.AddPoints( towards: ThisCard, LOVE points: 30 ) }",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::AddCardPoints*/
            ),
            new Action(
                    17, ActionCategories.FLOW_CONTROL, "Conditional End Execution", "End Thread If (%p)",
                    "ends execution of this thread if the given condition evaluates to true.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::ConditionalEndExecution*/
            ),
            new Action(
                    18, ActionCategories.EVENT, "Set Npc Normal Response Success", "NPCNormalResponseSuccess = %p",
                    "When executed with a Npc Answers Event, this can be used to modify the answer the character will do. Adhers to the following priority and override each other: Normal < Strong < Absolute.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::SetNpcResponseSuccess*/
            ),
            new Action(
                    19, ActionCategories.EVENT, "Set Npc Normal Response Percent", "NPCNormalResponsePercent = %p",
                    "When executed with a Npc Answers Event, this can be used to modify the success percentage showed. Note that changing this value does not influence the Nps Answer, as it has allready been made. This Action only modifies the Percentage displayed in the UI. Adhers to the following priority and override each other: Normal < Strong < Absolute.",
                    List.of(Value.Types.INT)
                    /*&Thread::SetNpcResponsePercent*/
            ),
            new Action(
                    20, ActionCategories.NPCACTION, "Make Npc Move to Room", "%p.GoTo(%p)",
                    "If the target character is controlled by the Computer, this Action makes them walk to the specified Room. If the Character is allready walking somewhere, it will do this instead. Keep in mind that executing this Action will throw an event next tick; watch out for endless loops",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::NpcMoveRoom*/
            ),
            new Action(
                    21, ActionCategories.NPCACTION, "Make Npc do Action with no Target", "%p.Do(action: %p)",
                    "If the target character is controlled by the Computer, this Action makes them do an Action that does not require another character to execute. If the Character is allready walking somewhere, it will do this instead. Keep in mind that executing this Action will throw an event next tick; watch out for endless loops",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::NpcActionNoTarget*/
            ),
            new Action(
                    22, ActionCategories.NPCACTION, "Make Npc Talk to Character", "%p.Do(action: %p, to: %p)",
                    "If the target character is controlled by the Computer, this Action makes them walk to and start the given conversation with the target. If the Character is allready walking somewhere, it will do this instead. Keep in mind that executing this Action will throw an event next tick; watch out for endless loops",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::NpcTalkTo*/
            ),
            new Action(
                    23, ActionCategories.NPCACTION, "Make Npc Talk to Character about someone", "%p.Do(action: %p, to: %p, about: %p)",
                    "If the target character is controlled by the Computer, this Action makes them walk to and start the given conversation with the target about another character in class, such as asking for their opinion or spreading bad rumors. If the Character is allready walking somewhere, it will do this instead. Keep in mind that executing this Action will throw an event next tick; watch out for endless loops",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::NpcTalkToAbout*/
            ),
            new Action(
                    24, ActionCategories.MODIFY_CARD, "Set Card Storage Integer", "%p.SetInt(%p) = %p",
                    "Sets an entry in the cards storage. The card storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.INT)
                    /*&Thread::SetCardStorageInt*/
            ),
            new Action(
                    25, ActionCategories.MODIFY_CARD, "Set Card Storage Float", "%p.SetFloat(%p) = %p",
                    "Sets an entry in the cards storage. The card storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.FLOAT)
                    /*&Thread::SetCardStorageFloat*/
            ),
            new Action(
                    26, ActionCategories.MODIFY_CARD, "Set Card Storage String", "%p.SetString(%p) = %p",
                    "Sets an entry in the cards storage. The card storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.STRING)
                    /*&Thread::SetCardStorageString*/
            ),
            new Action(
                    27, ActionCategories.MODIFY_CARD, "Set Card Storage Bool", "%p.SetBool(%p) = %p",
                    "Sets an entry in the cards storage. The card storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.BOOL)
                    /*&Thread::SetCardStorageBool*/
            ),
            new Action(
                    28, ActionCategories.MODIFY_CARD, "Remove Card Storage Integer", "%p.DropInt(%p)",
                    "Removes an entry from the cards storage. If the given entry exists, but does not contain an int, this function will fail.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::RemoveCardStorageInt*/
            ),
            new Action(
                    29, ActionCategories.MODIFY_CARD, "Remove Card Storage Float", "%p.DropFloat(%p)",
                    "Removes an entry from the cards storage. If the given entry exists, but does not contain a float, this function will fail.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::RemoveCardStorageFloat*/
            ),
            new Action(
                    30, ActionCategories.MODIFY_CARD, "Remove Card Storage String", "%p.DropString(%p)",
                    "Removes an entry from the cards storage. If the given entry exists, but does not contain a string, this function will fail.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::RemoveCardStorageString*/
            ),
            new Action(
                    31, ActionCategories.MODIFY_CARD, "Remove Card Storage Bool", "%p.DropBool(%p)",
                    "Removes an entry from the cards storage. If the given entry exists, but does not contain a bool, this function will fail.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::RemoveCardStorageBool*/
            ),
            new Action(
                    32, ActionCategories.MODIFY_CHARACTER, "Set Virtue", "%p.Virtue = %p",
                    "Set selected character's virtue. 0- lowest, 1 - low, 2 - normal, 3 - high, 4 - highest.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardVirtue*/
            ),
            new Action(
                    33, ActionCategories.MODIFY_CHARACTER, "Set Trait", "%p.Trait(%p) = %p",
                    "Enable or disable selected character's trait.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetCardTrait*/
            ),
            new Action(
                    34, ActionCategories.MODIFY_CHARACTER, "Set Personality", "%p.Personality = %p",
                    "Set character's personality.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardPersonality*/
            ),
            new Action(
                    35, ActionCategories.MODIFY_CHARACTER, "Set Voice Pitch", "%p.Pitch = %p",
                    "Set character's voice pitch.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardVoicePitch*/
            ),
            new Action(
                    36, ActionCategories.MODIFY_CHARACTER, "Set Club", "%p.Club = %p",
                    "Set character's club.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardClub*/
            ),
            new Action(
                    37, ActionCategories.MODIFY_CHARACTER, "Set Club Value", "%p.ClubValue = %p",
                    "Set character's club value.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardClubValue*/
            ),
            new Action(
                    38, ActionCategories.MODIFY_CHARACTER, "Set Club Rank", "%p.ClubRank = %p",
                    "Set character's club rank.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardClubRank*/
            ),
            new Action(
                    39, ActionCategories.MODIFY_CHARACTER, "Set Intelligence", "%p.Intelligence = %p",
                    "Set character's intelligence.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardIntelligence*/
            ),
            new Action(
                    40, ActionCategories.MODIFY_CHARACTER, "Set Intelligence Value", "%p.IntelligenceValue = %p",
                    "Set character's intelligence value.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardIntelligenceValue*/
            ),
            new Action(
                    41, ActionCategories.MODIFY_CHARACTER, "Set Intelligence Rank", "%p.IntelligenceRank = %p",
                    "Set character's intelligence rank.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardIntelligenceRank*/
            ),
            new Action(
                    42, ActionCategories.MODIFY_CHARACTER, "Set Strength", "%p.Strength = %p",
                    "Set character's strength.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardStrength*/
            ),
            new Action(
                    43, ActionCategories.MODIFY_CHARACTER, "Set Strength Value", "%p.StrengthValue = %p",
                    "Set character's strength value.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardStrengthValue*/
            ),
            new Action(
                    44, ActionCategories.MODIFY_CHARACTER, "Set Strength Rank", "%p.StrengthRank = %p",
                    "Set character's strength rank.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardStrengthRank*/
            ),
            new Action(
                    45, ActionCategories.MODIFY_CHARACTER, "Set Sociability", "%p.Sociability = %p",
                    "Set character's sociability.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardSociability*/
            ),
            new Action(
                    46, ActionCategories.MODIFY_CHARACTER, "Set First Name", "%p.FirstName = %p",
                    "Set character's First Name.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardSecondName*/
            ),
            new Action(
                    47, ActionCategories.MODIFY_CHARACTER, "Set Last Name", "%p.LastName = %p",
                    "Set character's Last Name.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardFirstName*/
            ),
            new Action(
                    48, ActionCategories.MODIFY_CHARACTER, "Set Sex Orientation", "%p.Orientation = %p",
                    "Set character's sexual orientation.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardOrientation*/
            ),
            new Action(
                    49, ActionCategories.MODIFY_CHARACTER, "Set Description", "%p.Description = %p",
                    "Set character's description.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardDescription*/
            ),
            new Action(
                    50, ActionCategories.GENERAL, "Change Player Character", "%p.SetPC",
                    "Change current Player Character.",
                    List.of(Value.Types.INT)
                    /*&Thread::SetPC*/
            ),
            new Action(
                    51, ActionCategories.GENERAL, "Start H scene", "StartH(pc: %p, partner: %p)",
                    "Start H scene between 2 characters",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::StartHScene*/
            ),
            new Action(
                    52, ActionCategories.MODIFY_CHARACTER, "Set Sex Experience: Vaginal", "%p.SexXP = %p",
                    "Set vaginal experience for the character",
                    List.of(Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetCardSexExperience*/
            ),
            new Action(
                    53, ActionCategories.MODIFY_CHARACTER, "Set Sex Experience: Anal", "%p.AnalXP = %p",
                    "Set anal experience for the character",
                    List.of(Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetCardAnalSexExperience*/
            ),
            new Action(
                    54, ActionCategories.GENERAL, "Add Mood", "%p.AddMood(mood: %p, strength: %p)",
                    "Add mood strength.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::AddMood*/
            ),
            new Action(
                    55, ActionCategories.GENERAL, "Replace Mood", "%p.ReplaceMood(mood: %p, with mood: %p, strength: %p)",
                    "Replace mood 1 with mood 2 up to strength.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::ReplaceMood*/
            ),
            new Action(
                    56, ActionCategories.MODIFY_CARD, "Set Item - Lover's", "%p.LoverItem = %p",
                    "Rename Lover's item.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardLoversItem*/
            ),
            new Action(
                    57, ActionCategories.MODIFY_CARD, "Set Item - Friend's", "%p.FriendItem = %p",
                    "Rename Friend's item.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardFriendItem*/
            ),
            new Action(
                    58, ActionCategories.MODIFY_CARD, "Set Item - Sexual", "%p.SexualItem = %p",
                    "Rename Sexual item.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardSexualItem*/
            ),
            new Action(
                    59, ActionCategories.MODIFY_CARD, "Set H Compatibility", "%p.Compatibility(%p) = %p",
                    "Set card's H compatibility with the selected character. 0-999 values",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardSexCompatibility*/
            ),
            new Action(
                    60, ActionCategories.NPCACTION, "Set NPC status", "%p.NpcStatus = %p",
                    "Set NPC status. 0=still, 1=settle in location, 2=move to location, 3=walk to character, 4=follow, 7=talk, 8=minna",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetNpcStatus*/
            ),
            new Action(
                    61, ActionCategories.NPCACTION, "Voyeur Clean Up", "Voyeur Clean Up",
                    "Cleans up after voyeur sex",
                    List.of()
                    /*&Thread::ResetVoyeur*/
            ),
            new Action(
                    62, ActionCategories.MODIFY_CHARACTER, "Set Points", "%p.SetPoints(towards: %p, LOVE: %p, LIKE: %p, DISLIKE: %p, HATE: %p, SPARE: %p)",
                    "Sets the full set of relationship points. The points are normalized, meaning you don't have to have them add up to 900 - the action will do it for you using the values as weights.\nIf you do have them add up to 900.0 they would be aplied as you provide them, minus the decimals.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.FLOAT, Value.Types.FLOAT, Value.Types.FLOAT, Value.Types.FLOAT, Value.Types.FLOAT)
                    /*&Thread::SetCardPoints*/
            ),
            new Action(
                    63, ActionCategories.GENERAL, "Write Log", "Log(%p)",
                    "Writes the string to the INFO log.",
                    List.of(Value.Types.STRING)
                    /*&Thread::WriteLog*/
            ),
            new Action(
                    64, ActionCategories.MODIFY_CHARACTER, "Cum Stat - Vaginal", "%p.SetVaginalCums(target: %p, amount: %p)",
                    "Sets the amount of times this character got cummed inside their vagina by the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatInVagina*/
            ),
            new Action(
                    65, ActionCategories.MODIFY_CHARACTER, "Cum Stat - Anal", "%p.SetAnalCums(target: %p, amount: %p)",
                    "Sets the amount of times this character got cummed inside their rectum by the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatInAnal*/
            ),
            new Action(
                    66, ActionCategories.MODIFY_CHARACTER, "Cum Stat - Oral", "%p.SetOralCums(target: %p, amount: %p)",
                    "Sets the amount of times this character got cummed inside their mouth by the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatInMouth*/
            ),
            new Action(
                    67, ActionCategories.MODIFY_CHARACTER, "Cum Stat - All", "%p.SetTotalCums(target: %p, amount: %p)",
                    "Sets the amount of times this character got cummed inside by the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatTotalCum*/
            ),
            new Action(
                    68, ActionCategories.MODIFY_CHARACTER, "Climax Stat - Single", "%p.SetSingleClimax(target: %p, amount: %p)",
                    "Sets the amount of times this character climaxed while having sex with the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatClimaxCount*/
            ),
            new Action(
                    69, ActionCategories.MODIFY_CHARACTER, "Climax Stat - Simultaneous", "%p.SetSimClimax(target: %p, amount: %p)",
                    "Sets the amount of times this character climaxed together with the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatSimClimaxCount*/
            ),
            new Action(
                    70, ActionCategories.MODIFY_CHARACTER, "H Stat - Condoms Used", "%p.SetCondomsUsed(target: %p, amount: %p)",
                    "Sets the amount of times this character used condoms with the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatCondomsUsed*/
            ),
            new Action(
                    71, ActionCategories.MODIFY_CHARACTER, "H Stat - Vaginal", "%p.SetVaginalH(target: %p, amount: %p)",
                    "Sets the amount of times this character had vaginal sex with the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardVaginalSex*/
            ),
            new Action(
                    72, ActionCategories.MODIFY_CHARACTER, "H Stat - Anal", "%p.SetAnalH(target: %p, amount: %p)",
                    "Sets the amount of times this character had anal sex with the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardAnalSex*/
            ),
            new Action(
                    73, ActionCategories.MODIFY_CHARACTER, "H Stat - All", "%p.SetAllH(target: %p, amount: %p)",
                    "Sets the amount of times this character had sex with the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardAllSex*/
            ),
            new Action(
                    74, ActionCategories.MODIFY_CHARACTER, "Cum Stat - Risky", "%p.SetRiskyCums(target: %p, amount: %p)",
                    "Sets the amount of times this character got cummed inside on their risky days by the target.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardCumStatRiskyCums*/
            ),
            new Action(
                    75, ActionCategories.MODIFY_CHARACTER, "Set Lover", "%p.SetLover(target: %p) = %p",
                    "Set this card's lover flag for the target. NOTE: Lovers status can be one-sided.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetLover*/
            ),
            new Action(
                    76, ActionCategories.NPCACTION, "Sex Consensual", "SexConsensual = %p",
                    "Set whether the sex will be consensual or not. 0 - consensual, 4 - Actor 0 will be raped, 8 - PC will be raped, 10 - both will be raped. ",
                    List.of(Value.Types.INT)
                    /*&Thread::IsConsensualH*/
            ),
            new Action(
                    77, ActionCategories.NPCACTION, "Lock character", "%p.LockState = %p",
                    "Locks the character from interacting. Adds the red circle around them. 1 - set red circle, 0 - unset it.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCharacterLocked*/
            ),
            new Action(
                    78, ActionCategories.NPCACTION, "Set Fap State", "%p.FapState = %p",
                    "Set masturbation state of the character. 1 - to fap, -1 - not to fap.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetMasturbating*/
            ),
            new Action(
                    79, ActionCategories.NPCACTION, "Set Auto-PC state", "AutoPC = %p",
                    "Toggle AutoPC on or off.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::AutoPC*/
            ),
            new Action(
                    80, ActionCategories.EVENT, "Set Normal PC Response", "PCNormalResponse = %p",
                    "Sets the PC's response. Responses adhere to the following priority and override each other: Normal < Strong < Absolute. Use normal unless you know what you're doing. 0 is 'Yes', 1 is 'No', 2 is 'Huh ?', -1 is undefined",
                    List.of(Value.Types.INT)
                    /*&Thread::SetPCResponse*/
            ),
            new Action(
                    81, ActionCategories.MODIFY_CHARACTER, "Set Clothing State", "%p.ClothingState = %p",
                    "Set the clothing state of some card.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetClothingState*/
            ),
            new Action(
                    82, ActionCategories.NPCACTION, "Set Cherry Status", "%p.SetCherryStatus(target: %p) = %p",
                    "Sets whether the character's virginity was attempted to be taken by the target. 0 - no, 1 - yes.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCherryStatus*/
            ),
            new Action(
                    83, ActionCategories.MODIFY_CHARACTER, "Set Cum", "%p.SetCum = %p",
                    "Set whether the character has cum in mouth.",
                    List.of(Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetCum*/
            ),
            new Action(
                    84, ActionCategories.MODIFY_CHARACTER, "Set Tears", "%p.SetTears = %p",
                    "Set whether the character is crying.",
                    List.of(Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetTears*/
            ),
            new Action(
                    85, ActionCategories.MODIFY_CHARACTER, "Set Highlight", "%p.SetHighlight = %p",
                    "Set whether the character has highlight in their eyes.",
                    List.of(Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetHighlight*/
            ),
            new Action(
                    86, ActionCategories.MODIFY_CHARACTER, "Set Glasses", "%p.SetGlasses = %p",
                    "Set whether the character has their glasses on.",
                    List.of(Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetGlasses*/
            ),
            new Action(
                    87, ActionCategories.NPCACTION, "Set Pose", "%p.Pose = %p",
                    "Sets pose for the scene actor.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetPose*/
            ),
            new Action(
                    88, ActionCategories.NPCACTION, "Cancel NPC's action", "%p.CancelAction",
                    "Cancels NPC's currently issued action",
                    List.of(Value.Types.INT)
                    /*&Thread::NpcCancelAction*/
            ),
            new Action(
                    89, ActionCategories.EVENT, "Set Npc Current Response Answer", "CurrentResponseAnswer = %p",
                    "When executed with a Npc Answers Event, this can be used to modify the answer the character will do.",
                    List.of(Value.Types.INT)
                    /*&Thread::SetNpcResponseAnswer*/
            ),
            new Action(
                    90, ActionCategories.MODIFY_CHARACTER, "Turn on H-AI", "H-AI = %p",
                    "Turn on H-AI",
                    List.of(Value.Types.BOOL)
                    /*&Thread::SetH_AI*/
            ),
            new Action(
                    91, ActionCategories.MODIFY_CHARACTER, "Change PC Target", "PCTarget = %p",
                    "Changes PC's target. Very situational use, cannot be used to redirect PC's actions. Can be used to change second actor of h.",
                    List.of(Value.Types.INT)
                    /*&Thread::SetPCTarget*/
            ),
            new Action(
                    92, ActionCategories.EVENT, "Set Strong PC Response", "PCStrongResponse = %p",
                    "Sets the PC's response. Responses adhere to the following priority and override each other: Normal < Strong < Absolute. Use normal unless you know what you're doing. 0 is 'Yes', 1 is 'No', 2 is 'Huh ?', -1 is undefined",
                    List.of(Value.Types.INT)
                    /*&Thread::SetStrongResponse*/
            ),
            new Action(
                    93, ActionCategories.EVENT, "Set Absolute PC Response", "PCAbsoluteResponse = %p",
                    "Sets the PC's response. Responses adhere to the following priority and override each other: Normal < Strong < Absolute. Use normal unless you know what you're doing. 0 is 'Yes', 1 is 'No', 2 is 'Huh ?', -1 is undefined",
                    List.of(Value.Types.INT)
                    /*&Thread::SetAbsoluteResponse*/
            ),
            new Action(
                    94, ActionCategories.EVENT, "Set Npc Strong Response Success", "NPCStrongResponseSuccess = %p",
                    "When executed with a Npc Answers Event, this can be used to modify the answer the character will do. Adhers to the following priority and override each other: Normal < Strong < Absolute.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::SetNpcStrongResponseSuccess*/
            ),
            new Action(
                    95, ActionCategories.EVENT, "Set Npc Strong Response Percent", "NPCStrongResponsePercent = %p",
                    "When executed with a Npc Answers Event, this can be used to modify the success percentage showed. Note that changing this value does not influence the Nps Answer, as it has allready been made. This Action only modifies the Percentage displayed in the UI. Adhers to the following priority and override each other: Normal < Strong < Absolute.",
                    List.of(Value.Types.INT)
                    /*&Thread::SetNpcResponseStrongPercent*/
            ),
            new Action(
                    96, ActionCategories.EVENT, "Set Npc Absolute Response Percent", "NPCAbsoluteResponsePercent = %p",
                    "When executed with a Npc Answers Event, this can be used to modify the success percentage showed. Note that changing this value does not influence the Nps Answer, as it has allready been made. This Action only modifies the Percentage displayed in the UI. Adhers to the following priority and override each other: Normal < Strong < Absolute.",
                    List.of(Value.Types.INT)
                    /*&Thread::SetNpcResponseAbsolutePercent*/
            ),
            new Action(
                    97, ActionCategories.EVENT, "Set Npc Absolute Response Success", "NPCAbsoluteResponseSuccess = %p",
                    "When executed with a Npc Answers Event, this can be used to modify the answer the character will do. Adhers to the following priority and override each other: Normal < Strong < Absolute.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::SetNpcAbsoluteResponseSuccess*/
            ),
            new Action(
                    98, ActionCategories.MODIFY_CHARACTER, "Set H Preference", "%p.HPreference(preference: %p) = %p",
                    "Enable or disable selected character's H preference.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.BOOL)
                    /*&Thread::SetCardPreference*/
            ),
            new Action(
                    99, ActionCategories.MODIFY_CHARACTER, "Set Opinion", "%p.Opinion(id: %p, towards: %p) = %p",
                    "Set the state of opinion of first character towards the second character.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardOpinion*/
            ),
            new Action(
                    100, ActionCategories.MODIFY_CHARACTER, "Set Decals", "%p.Decals(position: %p) = %p",
                    "Adds decals to a character to a certain part of their body. Use only on characters that are currently loaded in high poly. For position 0 - chest, 1 - back, 2 - crotch / legs, 3 - butt, 4 - face. Decals have multiple possible strengths (0-3) 0 being no decals and 3 being strongest.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetDecals*/
            ),
            new Action(
                    101, ActionCategories.NPCACTION, "Action About Room", "%p.ActionAboutRoom = %p",
                    "Set the room that the NPC will talk about in their action.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetActionAboutRoom*/
            ),
            new Action(
                    102, ActionCategories.MODIFY_CHARACTER, "H-AI Exit Lock", "H-AI Lock = %p",
                    "Set whether PC is locked in h while h-ai is on.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::Set_H_AI_LOCK*/
            ),
            new Action(
                    103, ActionCategories.NPCACTION, "Set Room Target", "%p.SetRoomTarget = %p",
                    "Set the room that the NPC will walk to. //Probably doesn't work.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetRoomTarget*/
            ),
            new Action(
                    104, ActionCategories.MODIFY_CHARACTER, "Set Fighting Stance", "%p.FightStance = %p",
                    "Set character's fighting stance.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardFightingStyle*/
            ),
            new Action(
                    105, ActionCategories.EVENT, "Swap Dominant", "SwapDominant",
                    "Swap who is dominant and submissive in an H scene.",
                    List.of()
                    /*&Thread::SwitchActiveInH*/
            ),
            new Action(
                    106, ActionCategories.MODIFY_CHARACTER, "Set Stamina", "%p.SetStamina = %p",
                    "Set the current stamina of a character.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetStamina*/
            ),
            new Action(
                    107, ActionCategories.EVENT, "Set period timer", "PeriodTimer = %p",
                    "Set the amount of seconds that have passed in the current period.",
                    List.of(Value.Types.INT)
                    /*&Thread::SetPeriodTimer*/
            ),
            new Action(
                    108, ActionCategories.GENERAL, "Notification", "Notification(text: %p, isImportant: %p)",
                    "Display the notification on the screen.",
                    List.of(Value.Types.STRING, Value.Types.BOOL)
                    /*&Thread::Notification*/
            ),
            new Action(
                    109, ActionCategories.GENERAL, "Call LUA Procedure", "LUA(%p)",
                    "Call supplemental lua procedure.",
                    List.of(Value.Types.STRING)
                    /*&Thread::CallLuaProcedure*/
            ),
            new Action(
                    110, ActionCategories.GENERAL, "Set Class Storage Integer", "SetInt(%p) = %p",
                    "Sets an entry in the class storage. The class storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.STRING, Value.Types.INT)
                    /*&Thread::SetClassStorageInt*/
            ),
            new Action(
                    111, ActionCategories.GENERAL, "Set Class Storage Float", "SetFloat(%p) = %p",
                    "Sets an entry in the class storage. The class storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.STRING, Value.Types.FLOAT)
                    /*&Thread::SetClassStorageFloat*/
            ),
            new Action(
                    112, ActionCategories.GENERAL, "Set Class Storage String", "SetString(%p) = %p",
                    "Sets an entry in the class storage. The class storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.STRING, Value.Types.STRING)
                    /*&Thread::SetClassStorageString*/
            ),
            new Action(
                    113, ActionCategories.GENERAL, "Set Class Storage Bool", "SetBool(%p) = %p",
                    "Sets an entry in the class storage. The class storage stores key-value pairs and is persistent between saves and loads. Note that the keys are shared between value types, so that for example a given key can not hold both an int and a string. When the key is allready in use, the function will silently fail.",
                    List.of(Value.Types.STRING, Value.Types.BOOL)
                    /*&Thread::SetClassStorageBool*/
            ),
            new Action(
                    114, ActionCategories.MODIFY_CHARACTER, "Add Stat Modifier: Virtue", "%p.AddVirtueMod(%p) = %p",
                    "Add or replace a virtue modifier",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.INT)
                    /*&Thread::AddVirtueMod*/
            ),
            new Action(
                    115, ActionCategories.MODIFY_CHARACTER, "Add Stat Modifier: Sociability", "%p.AddSociabilityMod(%p) = %p",
                    "Add or replace a sociability modifier",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.INT)
                    /*&Thread::AddSociabilityMod*/
            ),
            new Action(
                    116, ActionCategories.MODIFY_CHARACTER, "Add Stat Modifier: Strength", "%p.AddStrengthMod(%p) = %p",
                    "Add or replace a strength value modifier",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.INT)
                    /*&Thread::AddStrengthMod*/
            ),
            new Action(
                    117, ActionCategories.MODIFY_CHARACTER, "Add Stat Modifier: Intelligence", "%p.AddIntelligenceMod(%p) = %p",
                    "Add or replace an intelligence value modifier",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.INT)
                    /*&Thread::AddIntelligenceMod*/
            ),
            new Action(
                    118, ActionCategories.MODIFY_CHARACTER, "Add Stat Modifier: Club", "%p.AddClubMod(%p) = %p",
                    "Add or replace a club value modifier",
                    List.of(Value.Types.INT, Value.Types.STRING, Value.Types.INT)
                    /*&Thread::AddClubMod*/
            ),
            new Action(
                    119, ActionCategories.EVENT, "Kick out", "KickOutCard = %p",
                    "Kick the card with the specified seat out from the class.",
                    List.of(Value.Types.INT)
                    /*&Thread::KickOut*/
            ),
            new Action(
                    120, ActionCategories.EVENT, "Add card to class", "%p.AddCardToClass(%p) = %p",
                    "Add a card to the specified seat, with the specified gender. Input is Seat, Gender (1 for female, 0 for male) Filename respectively.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.STRING)
                    /*&Thread::AddToClass*/
            ),
            new Action(
                    121, ActionCategories.EVENT, "Change h position", "SexPosition = %p",
                    "Changes the h position to the one you specify. Only works in H position chang event.",
                    List.of(Value.Types.INT)
                    /*&Thread::ChangeHPosition*/
            ),
            new Action(
                    122, ActionCategories.MODIFY_CHARACTER, "Add Trait Modifier", "%p.AddTraitMod(trait: %p, modName: %p) = %p",
                    "Add or replace a trait modifier.",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.STRING, Value.Types.INT)
                    /*&Thread::AddTraitMod*/
            ),
            new Action(
                    123, ActionCategories.MODIFY_CHARACTER, "Set Gust of Wind", "Gust = %p",
                    "Causes the gust of wind event to happen. Use only in pc convo state updated or pc line updated events.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::SetGustOfWind*/
            ),
            new Action(
                    124, ActionCategories.MODIFY_CHARACTER, "Set First H Partner", "%p.HFirst = %p",
                    "Set character's First H Partner.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardFirstHPartner*/
            ),
            new Action(
                    125, ActionCategories.MODIFY_CHARACTER, "Set First Anal Partner", "%p.AnalFirst = %p",
                    "Set character's First Anal Partner.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardFirstAnalPartner*/
            ),
            new Action(
                    126, ActionCategories.MODIFY_CHARACTER, "Set Latest H Partner", "%p.HLast = %p",
                    "Set character's Latest H Partner.",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::SetCardLatestHPartner*/
            ),
            new Action(
                    127, ActionCategories.MODIFY_CHARACTER, "Set Partner Count", "%p.SetPartnerCount = %p",
                    "Set Partner Count of a character",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardPartnerCount*/
            ),
            new Action(
                    128, ActionCategories.MODIFY_CHARACTER, "Set H Partner Count", "%p.SetHPartnerCount = %p",
                    "Set Sexual Partner Count of a character",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetCardHPartnerCount*/
            ),
            new Action(
                    129, ActionCategories.MODIFY_CHARACTER, "Set Victory Count", "%p.SetVictoryCount = %p",
                    "Set Victory Count of a card (how many times it won a fight)",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetVictoryCount*/
            ),
            new Action(
                    130, ActionCategories.MODIFY_CHARACTER, "Set Classes Skipped", "%p.SetClassesSkipped = %p",
                    "Set Classes Skipped Count of a card",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetClassesSkipped*/
            ),
            new Action(
                    131, ActionCategories.MODIFY_CHARACTER, "Set Winning Count", "%p.SetWinningCount = %p",
                    "Set Winning Over Someone Count of a card (how many times it won in an argument",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetWinningCount*/
            ),
            new Action(
                    132, ActionCategories.MODIFY_CHARACTER, "Set Reject Count", "%p.SetRejectCount = %p",
                    "Set Reject Count of a card",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetRejectCount*/
            ),
            new Action(
                    133, ActionCategories.MODIFY_CHARACTER, "Set Academic Exam Grade", "%p.SetAcademicGrade = %p",
                    "Set Academic Exam Grade of a card",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetAcademicGrade*/
            ),
            new Action(
                    134, ActionCategories.MODIFY_CHARACTER, "Set Sports Exam Grade", "%p.SetSportsGrade = %p",
                    "Set Sport Exam Grade of a card",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetSportGrade*/
            ),
            new Action(
                    135, ActionCategories.MODIFY_CHARACTER, "Set Club Exam Grade", "%p.SetClubGrade = %p",
                    "Set Club Competition Grade of a card",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetClubGrade*/
            ),
            new Action(
                    136, ActionCategories.EVENT, "Set Applied Relationship Data", "ApplyRelationshipData(LOVE: %p, LIKE: %p, DISLIKE: %p, HATE: %p)",
                    "In Relationship Points Changed event change the resulting relationship shift of the interaction",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::RelationshipPointChange*/
            ),
            new Action(
                    137, ActionCategories.EVENT, "Arrange Date", "%p.ArrangeDateWith = %p",
                    "Makes the first character arrange a date with the other character.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::ArrangeDate*/
            ),
            new Action(
                    138, ActionCategories.EVENT, "Promise Lewd Reward", "%p.LewdPromise = %p",
                    "Makes the first character promise a lewd reward to the other character.",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::PromiseLewd*/
            ),
            new Action(
                    139, ActionCategories.GENERAL, "Emit Delayed Optional Event", "EmitOptional(delay: %p, label: %p)",
                    "Emit a delayed event. Delay time is in milliseconds. On period change may spill into the next period",
                    List.of(Value.Types.INT, Value.Types.STRING)
                    /*&Thread::EmitDelayedOptionalEvent*/
            ),
            new Action(
                    140, ActionCategories.GENERAL, "Emit Delayed Required Event", "EmitRequired(delay: %p, label: %p)",
                    "Emit a delayed event. Delay time is in milliseconds. On period change this event is executed sooner.",
                    List.of( Value.Types.INT, Value.Types.STRING )
                    /*&Thread::EmitDelayedRequiredEvent*/
            ),
            new Action(
                    141, ActionCategories.NPCACTION, "Set Condom-Override state", "CondomOverride = %p",
                    "Toggle Condom-Override on or off.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::CondomOverride*/
            ),
            new Action(
                    142, ActionCategories.NPCACTION, "Set Condom-Value state", "CondomValue = %p",
                    "Toggles condoms on or off, provided CondomOverride is turned on.",
                    List.of(Value.Types.BOOL)
                    /*&Thread::CondomValue*/
            ),
            new Action(
                    143, ActionCategories.MODIFY_CHARACTER, "Set Skirt State", "%p.SkirtState = %p",
                    "Set the skirt state of some card. 0 - long, 1 - short, 2 - rolled up, 3 - no skirt",
                    List.of(Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetSkirtState*/
            ),
            new Action(
                    144, ActionCategories.MODIFY_CHARACTER, "Set Pregnancy Risk", "%p.PregnancyRisk(day: %p) = %p",
                    "Set the pregnancy risk on the specified day of the 14 day cycle. 2 = dangerous, 1 = safe, 0 = normal",
                    List.of(Value.Types.INT, Value.Types.INT, Value.Types.INT)
                    /*&Thread::SetPregnancyRisk*/
            )
        };

        public static Action fromId(int id){
                if(id < 1) return null;
                if(id > g_Actions.length) return null;
                return g_Actions[id-1];
        }

    enum ActionCategories implements Category{
        GENERAL("General"),
        MODIFY_CARD("Card Modification"),
        FLOW_CONTROL("Flow Control "),
        MODIFY_CHARACTER("Character Modification"),
        EVENT("Event"),
        NPCACTION("Npc Action");

        private final String name;
        ActionCategories(String name){
            this.name = name;
        }
    }

    enum Actions{
        INVALID(0),
        SETVAR(1),
        IF(2),
        ELSEIF(3),
        ELSE(4),
        CONDJUMP(5),
        LOOP(6),
        CONDBREAK(7),
        CONDCONTINUE(8),
        FORLOOP(9);

        final int id;
        Actions(int id){
            this.id = id;
        }
    }
}
